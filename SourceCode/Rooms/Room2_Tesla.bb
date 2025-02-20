
Function FillRoom_Room2_Tesla(r.Rooms)
	Local w.WayPoints,w2.WayPoints,r2.Rooms
	
	r\Objects[0] = CreatePivot()
	PositionEntity(r\Objects[0], r\x, 0.0, r\z)
	EntityParent(r\Objects[0], r\obj)	
	
	r\Objects[1] = CreateSprite()
	EntityTexture (r\Objects[1], TeslaTexture)
	SpriteViewMode(r\Objects[1],2)
	EntityBlend (r\Objects[1], 3) 
	EntityFX(r\Objects[1], 1 + 8 + 16)
	
	PositionEntity(r\Objects[1], r\x, 0.8, r\z)
	
	HideEntity r\Objects[1]
	EntityParent(r\Objects[1], r\obj)
	
	w.WayPoints = CreateWaypoint(r\x, r\y + 66.0 * RoomScale, r\z + 292.0 * RoomScale, Null, r)
	w2.WayPoints = CreateWaypoint(r\x, r\y + 66.0 * RoomScale, r\z - 284.0 * RoomScale, Null, r)
	w\connected[0] = w2 : w\dist[0] = EntityDistance(w\obj, w2\obj) ;TODO waypoints Squared?
	w2\connected[0] = w : w2\dist[0] = w\dist[0]
	
	r\Objects[2] = CreateSprite()
	PositionEntity(r\Objects[2], r\x - 32 * RoomScale, 568 * RoomScale, r\z)
	ScaleSprite(r\Objects[2], 0.03, 0.03)
	EntityTexture(r\Objects[2], LightSpriteTex[1])
	EntityBlend (r\Objects[2], 3)
	EntityParent(r\Objects[2], r\obj)
	HideEntity r\Objects[2]
	
	For r2.Rooms = Each Rooms
		If r2<>r Then
			If r2\RoomTemplate\Name = "room2tesla_ez" Lor r2\RoomTemplate\Name = "room2tesla_lcz" Lor r2\RoomTemplate\Name = "room2tesla_hcz" Then
				r\Objects[3] = CopyEntity(r2\Objects[3],r\obj) ;don't load the mesh again
				Exit
			EndIf
		EndIf
	Next
	If r\Objects[3]=0 Then r\Objects[3] = LoadMesh_Strict("GFX\map\room2tesla_caution.b3d",r\obj)
	
End Function

Function UpdateEvent_Room2_Tesla(e.Events)
	Local e2.Events,p.Particles,n.NPCs,d.Decals
	Local temp%
	Local i
	
	If e\room\dist < 16 Then
		If (MilliSecs() Mod 1500) < 800 And e\EventState = 0 Lor (MilliSecs() Mod 100) < 50 And e\EventState = 1 Then
			ShowEntity e\room\Objects[2]
		Else
			HideEntity e\room\Objects[2]
		EndIf
		
		Select e\EventState
			Case 0 ; Idle state
				HideEntity e\room\Objects[1]
				e\SoundCHN = LoopSound2(TeslaIdleSFX,e\SoundCHN,Camera,e\room\Objects[1],4.0,0.5)
				e\EventState2 = 0
				If Abs(EntityX(Collider,True)-EntityX(e\room\Objects[0],True)) < 1.0 And (e\room\angle Mod 180 = 90) Lor Abs(EntityZ(Collider,True)-EntityZ(e\room\Objects[0],True)) < 1.0 And (e\room\angle Mod 180 = 0) Then
					If EntityDistanceSquared(Collider,e\room\Objects[0]) < PowTwo(300.0*RoomScale) And KillTimer => 0 Then
						e\EventState = 1
						StopChannel(e\SoundCHN)
						e\SoundCHN = PlaySound2(TeslaActivateSFX, Camera, e\room\Objects[1],4.0,0.5)
					EndIf
				EndIf
				For n.NPCs = Each NPCs
					If n\Collider <> 0 And n\IsDead = False And n\HP > 0 And n\NPCtype <> NPCtype966 Then
						If Abs(EntityX(n\Collider,True)-EntityX(e\room\Objects[0],True)) < 0.8 And (e\room\angle Mod 180 = 90) Lor Abs(EntityZ(n\Collider,True)-EntityZ(e\room\Objects[0],True)) < 0.8 And (e\room\angle Mod 180 = 0) Then
							If EntityDistanceSquared(n\Collider,e\room\Objects[0]) < PowTwo(300.0*RoomScale)
								e\EventState = 1
								StopChannel(e\SoundCHN)
								e\SoundCHN = PlaySound2(TeslaActivateSFX, Camera, e\room\Objects[1],4.0,0.5)
							EndIf
						EndIf	
					EndIf	
				Next
			Case 1 ; Charge state
				e\EventState2 = e\EventState2 + FPSfactor
				If e\EventState2 >= 35 Then
					e\EventState = 2
					PlaySound2(LoadTempSound("SFX\Room\Tesla\Shock.ogg"),Camera,e\room\Objects[1])
				EndIf	
			Case 2 ; Zap state
				If Abs(EntityX(Collider,True)-EntityX(e\room\Objects[0],True)) < 0.75 And (e\room\angle Mod 180 = 90) Lor Abs(EntityZ(Collider,True)-EntityZ(e\room\Objects[0],True)) < 0.75 And (e\room\angle Mod 180 = 0) Then
					If EntityDistanceSquared(Collider,e\room\Objects[0]) < PowTwo(300.0*RoomScale) And KillTimer => 0 Then
						LightFlash = 0.4
						CameraShake = 1.0
						Kill()
						DeathMSG = GetLocalStringR("Singleplayer", "tesla_death", Designation)
					EndIf	
				EndIf
				For n.NPCs = Each NPCs
					If n\Collider <> 0 And n\IsDead = False And n\HP > 0 Then
						If Abs(EntityX(n\Collider,True)-EntityX(e\room\Objects[0],True)) < 0.5 And (e\room\angle Mod 180 = 90) Lor Abs(EntityZ(n\Collider,True)-EntityZ(e\room\Objects[0],True)) < 0.5 And (e\room\angle Mod 180 = 0) Then
							If EntityDistanceSquared(n\Collider,e\room\Objects[0]) < PowTwo(300.0*RoomScale) Then
								Select n\NPCtype
									Case NPCtypeOldMan
										n\State = 70 * 60 * Rand(10,13)
										TranslateEntity(n\Collider,0,-10.0,0,True)
										d.Decals = CreateDecal(DECAL_DECAY, EntityX(n\Collider, True), 0.01, EntityZ(n\Collider, True), 90, Rand(360), 0)
										d\Size = 0.05 : d\SizeChange = 0.005 : EntityAlpha(d\obj, 0.8)
									Case NPCtypeD2
										n\HP = 0
										n\IsDead = True
										n\State2 = Rand(0,3)
									Case NPCtypeZombie, NPCtype008
										n\HP = 0
									Case NPCtype049
										n\HP = -1000
										n\State = SCP049_STUNNED
								End Select
								If e\room\dist < 8 And EntityInView(e\room\Objects[0],Camera) Then
									LightFlash = 0.4
								EndIf	
							EndIf	
						EndIf
					EndIf	
				Next	
				If Rand(5)<5 Then 
					PositionTexture TeslaTexture,0.0,Rnd(0,1.0)
					ShowEntity e\room\Objects[1]
				EndIf
				e\EventState2 = e\EventState2 - FPSfactor * 1.5
				If e\EventState2 <= 0 Then
					e\EventState = 3
					e\EventState2 = -70
					StopChannel(e\SoundCHN)
					e\SoundCHN = PlaySound2(TeslaPowerUpSFX, Camera, e\room\Objects[1],4.0,0.5)
				EndIf	
			Case 3 ; Recharge state
				e\EventState2 = e\EventState2 + FPSfactor
				HideEntity e\room\Objects[1]
				If e\EventState2 >= 0 Then
					e\EventState = 0
				EndIf	
		End Select
	EndIf
	
End Function

;~IDEal Editor Parameters:
;~F#1#2C
;~C#Blitz3D