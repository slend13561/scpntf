
Function FillRoom_Room2_Tesla(r.Rooms)
	Local w.WayPoints,w2.WayPoints,r2.Rooms
	
	r\Objects[0] = CreatePivot()
	PositionEntity(r\Objects[0], r\x - 114.0 * RoomScale, 0.0, r\z)
	EntityParent(r\Objects[0], r\obj)
	
	r\Objects[1] = CreatePivot()
	PositionEntity(r\Objects[1], r\x + 114.0 * RoomScale, 0.0, r\z)
	EntityParent(r\Objects[1], r\obj)			
	
	r\Objects[2] = CreatePivot()
	PositionEntity(r\Objects[2], r\x, 0.0, r\z)
	EntityParent(r\Objects[2], r\obj)	
	
	r\Objects[3] = CreateSprite()
	EntityTexture (r\Objects[3], TeslaTexture)
	SpriteViewMode(r\Objects[3],2)
	EntityBlend (r\Objects[3], 3) 
	EntityFX(r\Objects[3], 1 + 8 + 16)
	
	PositionEntity(r\Objects[3], r\x, 0.8, r\z)
	
	HideEntity r\Objects[3]
	EntityParent(r\Objects[3], r\obj)
	
	w.WayPoints = CreateWaypoint(r\x, r\y + 66.0 * RoomScale, r\z + 292.0 * RoomScale, Null, r)
	w2.WayPoints = CreateWaypoint(r\x, r\y + 66.0 * RoomScale, r\z - 284.0 * RoomScale, Null, r)
	w\connected[0] = w2 : w\dist[0] = EntityDistance(w\obj, w2\obj) ;TODO waypoints Squared?
	w2\connected[0] = w : w2\dist[0] = w\dist[0]
	
	r\Objects[4] = CreateSprite()
	PositionEntity(r\Objects[4], r\x - 32 * RoomScale, 568 * RoomScale, r\z)
	ScaleSprite(r\Objects[4], 0.03, 0.03)
	EntityTexture(r\Objects[4], LightSpriteTex[1])
	EntityBlend (r\Objects[4], 3)
	EntityParent(r\Objects[4], r\obj)
	HideEntity r\Objects[4]
	
	r\Objects[5] = CreatePivot()
	PositionEntity(r\Objects[5],r\x,0,r\z-800*RoomScale)
	EntityParent(r\Objects[5],r\obj)
	
	r\Objects[6] = CreatePivot()
	PositionEntity(r\Objects[6],r\x,0,r\z+800*RoomScale)
	EntityParent(r\Objects[6],r\obj)
	
	For r2.Rooms = Each Rooms
		If r2<>r Then
			If r2\RoomTemplate\Name = "room2tesla_ez" Lor r2\RoomTemplate\Name = "room2tesla_lcz" Lor r2\RoomTemplate\Name = "room2tesla_hcz" Then
				r\Objects[7] = CopyEntity(r2\Objects[7],r\obj) ;don't load the mesh again
				Exit
			EndIf
		EndIf
	Next
	If r\Objects[7]=0 Then r\Objects[7] = LoadMesh_Strict("GFX\map\room2tesla_caution.b3d",r\obj)
	
End Function

Function UpdateEvent_Room2_Tesla(e.Events)
	Local e2.Events,p.Particles,n.NPCs
	Local temp%
	Local i
	
	temp = True
	If e\EventState2 > 70*3.5 And e\EventState2 < 70*90 Then temp = False
	
	If temp And EntityY(Collider, True) > EntityY(e\room\obj,True) And EntityY(Collider, True) < 4.0 Then
		
		If e\Sound = 0 Then e\Sound = LoadSound_Strict("SFX\Room\Tesla\Shock.ogg")
		
		If e\EventState = 0 Then
			If e\room\dist < 8 Then
				HideEntity e\room\Objects[3]
				If (MilliSecs() Mod 1500) < 800 Then
					ShowEntity e\room\Objects[4]
				Else
					HideEntity e\room\Objects[4]
				EndIf			
				
				If e\SoundCHN = 0 Then ;humming when the player isn't close
					e\SoundCHN = PlaySound2(TeslaIdleSFX, Camera, e\room\Objects[3],4.0,0.5)
				Else
					If Not ChannelPlaying(e\SoundCHN) Then e\SoundCHN = PlaySound2(TeslaIdleSFX, Camera, e\room\Objects[3],4.0,0.5)
				EndIf
				
				For i = 0 To 2
					If DistanceSquared(EntityX(Collider),EntityX(e\room\Objects[i],True),EntityZ(Collider),EntityZ(e\room\Objects[i],True)) < PowTwo(300.0*RoomScale) Then
						;play the activation sound
						If KillTimer => 0 Then 
							PlayerSoundVolume = Max(8.0,PlayerSoundVolume)
							StopChannel(e\SoundCHN)
							e\SoundCHN = PlaySound2(TeslaActivateSFX, Camera, e\room\Objects[3],4.0,0.5)
							e\EventState = 1
							Exit
						EndIf
					EndIf
				Next
				
				Local temp2 = True
				For e2.Events = Each Events
					If e2\EventName = e\EventName And e2 <> e
						If e2\EventStr <> ""
							temp2 = False
							e\EventStr = "done"
							Exit
						EndIf
					EndIf
				Next
				
				Local temp3 = 0
				If temp2
					If e\EventStr = "" And PlayerRoom = e\room
						If EntityDistanceSquared(e\room\Objects[5],Collider)<EntityDistanceSquared(e\room\Objects[6],Collider)
							temp3 = 6
						Else
							temp3 = 5
						EndIf
						
						e\room\NPC[0] = CreateNPC(NPCtypeClerk,EntityX(e\room\Objects[temp3],True),0.5,EntityZ(e\room\Objects[temp3],True))
						PointEntity e\room\NPC[0]\Collider,e\room\Objects[2]
						e\room\NPC[0]\State = 2
						e\EventStr = "step1"
						e\EventState2 = 0
						e\EventState3 = 0
					EndIf
				EndIf
			Else
				HideEntity e\room\Objects[4]
			EndIf
			
			If Curr106\State <= -10 And e\EventState = 0 Then 
				For i = 0 To 2
					If DistanceSquared(EntityX(Curr106\Collider),EntityX(e\room\Objects[i],True),EntityZ(Curr106\Collider),EntityZ(e\room\Objects[i],True)) < PowTwo(300.0*RoomScale) Then
						;play the activation sound
						If KillTimer => 0 Then 
							StopChannel(e\SoundCHN)
							e\SoundCHN = PlaySound2(TeslaActivateSFX, Camera, e\room\Objects[3],4.0,0.5)
							HideEntity e\room\Objects[4]
							e\EventState = 1
							Curr106\State = 70 * 60 * Rand(10,13)
							GiveAchievement(AchvTesla)
							Exit
						EndIf
					EndIf
				Next
			EndIf
		Else
			e\EventState = e\EventState+FPSfactor
			If e\EventState =< 40 Then
				HideEntity e\room\Objects[3]
				If (MilliSecs() Mod 100) < 50 Then
					ShowEntity e\room\Objects[4]
				Else
					HideEntity e\room\Objects[4]
				EndIf
			Else
				If e\room\dist < 2
					If e\EventState-FPSfactor =< 40 Then PlaySound_Strict(e\Sound)	
				Else
					If e\EventState-FPSfactor =< 40 Then PlaySound2(e\Sound,Camera,e\room\Objects[2])
				EndIf
				If e\EventState < 70 Then 
					
					If KillTimer => 0 Then 
						For i = 0 To 2
							If DistanceSquared(EntityX(Collider),EntityX(e\room\Objects[i],True),EntityZ(Collider),EntityZ(e\room\Objects[i],True)) < PowTwo(250.0*RoomScale) Then
								LightFlash = 0.4
								CameraShake = 1.0
								Kill()
								DeathMSG = GetLocalStringR("Singleplayer", "tesla_death", Designation)
							EndIf
						Next
					EndIf
					
					If e\EventStr = "step1"
						e\room\NPC[0]\State = 3
					EndIf
					
					If Curr106\State <= -10 Then
						For i = 0 To 2
							If DistanceSquared(EntityX(Curr106\Collider),EntityX(e\room\Objects[i],True),EntityZ(Curr106\Collider),EntityZ(e\room\Objects[i],True)) < PowTwo(250.0*RoomScale) Then
								LightFlash = 0.3
								If ParticleAmount > 0
									For i = 0 To 5+(5*(ParticleAmount-1))
										p.Particles = CreateParticle(EntityX(Curr106\Collider, True), EntityY(Curr106\Collider, True), EntityZ(Curr106\Collider, True), 0, 0.015, -0.2, 250)
										p\size = 0.03
										p\gravity = -0.2
										p\lifetime = 200
										p\SizeChange = 0.005
										p\speed = 0.001
										RotateEntity(p\pvt, Rnd(360), Rnd(360), 0, True)
									Next
								EndIf
								Curr106\State = -20000
								TranslateEntity(Curr106\Collider,0,-50.0,0,True)
							EndIf
						Next								
					EndIf
					
					HideEntity e\room\Objects[3]
					HideEntity e\room\Objects[4]
					
					If Rand(5)<5 Then 
						PositionTexture TeslaTexture,0.0,Rnd(0,1.0)
						ShowEntity e\room\Objects[3]								
					EndIf
				Else 
					If e\EventState-FPSfactor < 70 Then 
						StopChannel(e\SoundCHN)	
						e\SoundCHN = PlaySound2(TeslaPowerUpSFX, Camera, e\room\Objects[3],4.0,0.5)
					EndIf 
					HideEntity e\room\Objects[3]
					
					If e\EventState > 150 Then e\EventState = 0
				EndIf
			EndIf
		EndIf
	Else
		HideEntity e\room\Objects[4]
	EndIf
	
	If e\room\NPC[0] <> Null
		If e\EventStr = "step1" And e\room\NPC[0]\State <> 3
			If e\EventState = 0
				For i = 0 To 2
					If DistanceSquared(EntityX(e\room\NPC[0]\Collider),EntityX(e\room\Objects[i],True),EntityZ(e\room\NPC[0]\Collider),EntityZ(e\room\Objects[i],True)) < PowTwo(400.0*RoomScale)
						StopChannel(e\SoundCHN)
						e\SoundCHN = PlaySound2(TeslaActivateSFX, Camera, e\room\Objects[3],4.0,0.5)
						HideEntity e\room\Objects[4]
						e\EventState = 1
						Exit
					EndIf
				Next
			EndIf
		ElseIf e\EventStr = "step1" And e\room\NPC[0]\State = 3
			e\room\NPC[0]\CurrSpeed = 0
			AnimateNPC(e\room\NPC[0],41,60,0.5,False)
			If e\room\NPC[0]\Frame = 60
				e\room\NPC[0]\IsDead = True
				e\EventStr = "step2"
				SetNPCFrame(e\room\NPC[0],57)
			EndIf
		ElseIf e\EventStr = "step2"
			AnimateNPC(e\room\NPC[0],57,60,0.5,False)
			If e\room\NPC[0]\Frame = 60
				e\EventStr = "0"
			EndIf
		ElseIf e\EventStr <> "" And e\EventStr <> "step1" And e\EventStr <> "done"
			If Float(e\EventStr) < 70*10 Then
				If ParticleAmount > 0 Then
					If Rand(20-(10*(ParticleAmount-1)))=1 Then
						p.Particles = CreateParticle(EntityX(e\room\NPC[0]\Collider),EntityY(e\room\NPC[0]\obj)+0.05,EntityZ(e\room\NPC[0]\Collider),0,0.05,0,60)
						p\speed = 0.002
						RotateEntity(p\pvt, 0, EntityYaw(e\room\NPC[0]\Collider), 0)
						MoveEntity p\pvt,Rnd(-0.1,0.1),0,0.1+Rnd(0,0.5)
						RotateEntity(p\pvt, -90, EntityYaw(e\room\NPC[0]\Collider), 0)
						p\Achange = -0.02
					EndIf
				EndIf
				e\EventStr = Float(e\EventStr) + FPSfactor
			Else
				e\EventStr = "done"
			EndIf
		EndIf
	EndIf
	
	If e\EventState3 > 0 Then
		e\EventState3=e\EventState3-FPSfactor
	EndIf
	
	If e\EventState2 => 70*92 And e\EventState2-FPSfactor < 70*92
		PlayAnnouncement("SFX\Character\MTF\Tesla"+Rand(1,3)+".ogg")
	EndIf
	
	e\EventState2 = Max(e\EventState2-FPSfactor,0)
	
End Function

;~IDEal Editor Parameters:
;~F#1
;~C#Blitz3D