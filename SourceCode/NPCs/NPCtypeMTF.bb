;MTF Unit constants
;[Block]
Const MTF_FOLLOWPLAYER = 0
Const MTF_INTRO = 1
Const MTF_CONTAIN173 = 2
Const MTF_CONTACT = 3
Const MTF_FLEE = 4
Const MTF_SPLIT_UP = 5
Const MTF_SEARCH = 6
Const MTF_HEAL = 7
Const MTF_TOTARGET = 8
;Containment constants
Const MTF_GOTOSCP = 1
;MTF Designation constants
Const MTF_UNIT_DEFAULT = 0
Const MTF_UNIT_REGULAR = 1
Const MTF_UNIT_MEDIC = 2
;[End Block]

Function CreateNPCtypeMTF(n.NPCs)
	Local temp#
	
	n\NPCName = "MTF"
	n\NVName = "Human"
	n\Collider = CreatePivot()
	EntityRadius n\Collider, 0.175, 0.2
	EntityType n\Collider, HIT_PLAYER
	n\obj = CopyEntity(MTFObj)
	
	n\Speed = (GetINIFloat("DATA\NPCs.ini", "MTF", "speed") / 100.0)
	temp# = (GetINIFloat("DATA\NPCs.ini", "MTF", "scale") / 2.5)
	ScaleEntity n\obj, temp, temp, temp
	MeshCullBox (n\obj, -MeshWidth(MTFObj), -MeshHeight(MTFObj), -MeshDepth(MTFObj), MeshWidth(MTFObj)*2, MeshHeight(MTFObj)*2, MeshDepth(MTFObj)*2) 
	
	SwitchNPCGun%(n, GUN_P90)
	
	n\HP = 550-(75*SelectedDifficulty\otherFactors)
	
	n\BlinkTimer = 70.0*Rnd(5,8)
	
	n\Clearance = 3
End Function

Function UpdateNPCtypeMTF(n.NPCs)
	Local n2.NPCs,w.WayPoints,e.Events,r.Rooms
	Local prevFrame# = n\Frame
	Local SeePlayer% = False
	Local SeeNPC%, VoiceLine$, SmallestNPCDist#, CurrentNPCDist#
	Local PlayerDistSquared# = EntityDistanceSquared(n\Collider,Collider)
	Local WayPDist#
	Local WaypointDist# = 0.0
	Local temp% = False
	Local sfxstep% = 0
	Local IsPushable% = False
	Local i%
	Local prevX#, prevZ#
	Local pvt%
	Local v3d_1.Vector3D, v3d_2.Vector3D
	
	n\BlinkTimer = Max(n\BlinkTimer-FPSfactor,0.0)
	If n\BlinkTimer = 0.0 Then
		If n\State = MTF_CONTAIN173 Then
			PlayMTFSound(LoadTempSound("SFX\Character\MTF\173\BLINKING.ogg"),n,True)
		EndIf
		n\BlinkTimer = 70.0*Rnd(5,8)
	EndIf
	
	If IsNPCStuck(n, 70.0*5) Then
		n\PathStatus = 0
		If n\State = MTF_FOLLOWPLAYER Then
			v3d_1 = CreateVector3D(962, 1259, 0.3)
			v3d_2 = CreateVector3D(1496, 1524, 0.3)
			NPC_GoTo(n, v3d_1, v3d_2, Collider, 0.7)
			Delete v3d_1
			Delete v3d_2
		ElseIf n\State = MTF_SPLIT_UP Then
			v3d_1 = CreateVector3D(962, 1259, 0.3)
			v3d_2 = CreateVector3D(1496, 1524, 0.3)
			NPC_GoToRoom(n, v3d_1, v3d_2, 0.7)
			Delete v3d_1
			Delete v3d_2
		EndIf
	EndIf
	
	n\Reload = Max(n\Reload-FPSfactor,0.0)
	n\PathTimer = Max(n\PathTimer-FPSfactor,0.0)
	
	If n\HP > 0 Then
		If n\State = MTF_FOLLOWPLAYER Lor n\State = MTF_HEAL Lor n\State = MTF_SEARCH Then
			;Spotting other NPCs
			SmallestNPCDist# = 0.0 : CurrentNPCDist# = 0.0
			For n2 = Each NPCs
				If n2\NPCtype <> n\NPCtype And n2\HP > 0 And IsTarget(n, n2) Then
					SeeNPC = OtherNPCSeesMeNPC(n2, n)
					If SeeNPC And EntityVisible(n\Collider, n2\Collider) Then
						CurrentNPCDist = EntityDistanceSquared(n\Collider, n2\Collider)
						If SmallestNPCDist = 0.0 Lor SmallestNPCDist > CurrentNPCDist Then
							SmallestNPCDist = CurrentNPCDist
							n\Target = n2
						EndIf
					EndIf
				EndIf
			Next
			For n2 = Each NPCs
				If n2 = n\Target Then
					If n\State = MTF_SEARCH Then
						SetNPCFrame(n, 1383)
					EndIf
					n\State = MTF_CONTACT
					VoiceLine = ""
					Select n2\NPCtype
						Case NPCtypeD2
							VoiceLine = "Stop" + Rand(1,6)
						Case NPCtypeZombie
							VoiceLine = "049\Player0492_1"
						Case NPCtype008
							;VoiceLine = "008\Spotted" ;Todo: Voice line required
						Case NPCtype939
							;VoiceLine = "939\Spotted" ;Todo: Voice line required
						Case NPCtypeOldMan
							VoiceLine = "106\Spotted" + Rand(1,4)
							n\State = MTF_FLEE
						Case NPCtype173
							VoiceLine = "173\Spotted" + Rand(1,2)
							n\State = MTF_CONTAIN173
						Case NPCtype049
							VoiceLine = "049\Spotted" + Rand(1,5)
							n\State = MTF_FLEE
						Case NPCtype096
							VoiceLine = "096\Spotted" + Rand(1,2)
							n\State = MTF_FLEE
					End Select
					If VoiceLine <> "" Then
						PlayMTFSound(LoadTempSound("SFX\Character\MTF\" + VoiceLine + ".ogg"), n, True)
					EndIf
					Exit
				EndIf
			Next
		EndIf
		
		Select n\State
			Case MTF_FOLLOWPLAYER, MTF_HEAL
				;[Block]
				If n\State = MTF_HEAL And (psp\Health = 100 Lor n\PrevState <> MTF_UNIT_MEDIC) Then
					n\State = MTF_FOLLOWPLAYER
				EndIf
				
				;Player can push MTF unit in this state
				IsPushable = True
				
				;Following the player
				If PlayerDistSquared <= PowTwo(8.0) Then
					If EntityVisible(n\Collider, Collider) Then
						SeePlayer = True
					EndIf
				EndIf
				If SeePlayer Then
					n\PathStatus = 0
					n\PathLocation = 0
					n\PathTimer = 0.0
					
					n\Angle = EntityYaw(n\Collider) + DeltaYaw(n\obj, Collider)
					RotateEntity n\Collider, 0, CurveAngle(n\Angle, EntityYaw(n\Collider), 20.0), 0
					If EntityDistanceSquared(n\Collider, Collider)<PowTwo(1.0 - (0.5 * n\State = MTF_HEAL)) Then
						If n\CurrSpeed <= 0.01 Then
							n\CurrSpeed = 0.0
							AnimateNPC(n, 962, 1259, 0.3)
							psp\Health = 100
							n\State = MTF_FOLLOWPLAYER
						Else
							n\CurrSpeed = CurveValue(0.0,n\CurrSpeed,20.0)
							AnimateNPC(n, 1496, 1524, n\CurrSpeed*30)
							MoveEntity n\Collider, 0, 0, n\CurrSpeed * FPSfactor
						EndIf
					Else
						n\CurrSpeed = CurveValue(n\Speed*0.7, n\CurrSpeed, 20.0)
						AnimateNPC(n, 1496, 1524, n\CurrSpeed*30)
						MoveEntity n\Collider, 0, 0, n\CurrSpeed * FPSfactor
					EndIf
				Else
					For e = Each Events
						If e <> Null And e\EventName = "room2_tesla" Then
							If Abs(EntityX(n\Collider)-EntityX(e\room\obj,True))<4.0 And Abs(EntityZ(n\Collider)-EntityZ(e\room\obj,True))<4.0 Then
								If e\EventState2 <= 70*3.5 Then
									n\Idle = 70*10
									PlayMTFSound(LoadTempSound("SFX\Character\MTF\Tesla0.ogg"), n)
									e\EventState2 = 70*100
									e\EventState3 = e\EventState3 + 140
									Exit
								EndIf
							EndIf
						EndIf
					Next
					
					v3d_1 = CreateVector3D(962, 1259, 0.3)
					v3d_2 = CreateVector3D(1496, 1524, 0.3)
					NPC_GoTo(n, v3d_1, v3d_2, Collider, 0.7)
					Delete v3d_1
					Delete v3d_2
				EndIf
				
				If Curr173\Idle = SCP173_STATIONARY Then
					v3d_1 = CreateVector3D(79, 309, 0.3)
					v3d_2 = CreateVector3D(488, 522, 0.3)
					NPC_GoTo(n, v3d_1, v3d_2, Collider, 0.7)
					Delete v3d_1
					Delete v3d_2
				EndIf
				
				If PlayerDistSquared > PowTwo(16) Then
					Local shortestDist# = 10000.0
					Local shortestDistRoom% = -1
					For i = 0 To 3
						If PlayerRoom\Adjacent[i] <> Null Then
							Local currDist# = EntityDistanceSquared(n\Collider, PlayerRoom\Adjacent[i]\obj)
							If currDist < shortestDist And (Not EntityInView(PlayerRoom\Adjacent[i]\obj, Camera)) Then
								shortestDist = currDist
								shortestDistRoom = i
							EndIf
						EndIf
					Next
					
					If shortestDistRoom >= 0 Then
						TeleportEntity(n\Collider, PlayerRoom\Adjacent[shortestDistRoom]\x, PlayerRoom\Adjacent[shortestDistRoom]\y + 0.1, PlayerRoom\Adjacent[shortestDistRoom]\z, n\CollRadius)
						n\PathStatus = 0
						n\PathLocation = 0
						n\PathTimer = 0.0
					EndIf
				EndIf
				;[End Block]
			Case MTF_INTRO
				;[Block]
				If n\State2 = 0 ;sitting
					AnimateNPC(n, 1525, 1623, 0.5)
				ElseIf n\State2 = 1 ;aiming
					AnimateNPC(n, 346, 351, 0.2)
				ElseIf n\State2 = 2 ;idle
					If Rand(400) = 1 Then
						n\Angle = n\Angle + Rnd(-30, 30)
					EndIf
					AnimateNPC(n, 962, 1259, 0.3)
					RotateEntity(n\Collider, 0, CurveAngle(n\Angle,EntityYaw(n\Collider),10.0), 0, True)
				EndIf
				;[End Block]
			Case MTF_CONTAIN173
				;[Block]
				For n2 = Each NPCs
					If n2<>n Then
						If n2\NPCtype = n\NPCtype Then
							If n2\State = MTF_CONTAIN173 Then
								If EntityDistanceSquared(n\Collider,Curr173\Collider)<PowTwo(7.0) And EntityDistanceSquared(n2\Collider,Curr173\Collider)<PowTwo(7.0) Then
									If EntityVisible(n\Collider,Curr173\Collider) And EntityVisible(n2\Collider,Curr173\Collider) Then
										Curr173\Idle = SCP173_STATIONARY
										If EntityDistanceSquared(Collider, Curr173\Collider)<PowTwo(7) Then
											If EntityInView(Curr173\Collider, Camera) Then
												Curr173\ContainmentState = SCP173_NOMOVE
											EndIf
										EndIf
										Exit
									EndIf
								EndIf
							ElseIf EntityDistanceSquared(Collider, Curr173\Collider)<PowTwo(7) And NPCSeesEntity(n, Curr173\Collider) Then
								If EntityInView(Curr173\Collider, Camera) Then
									Curr173\Idle = SCP173_STATIONARY
									Exit
								EndIf
							Else
								n\CurrSpeed = CurveValue(n\Speed, n\CurrSpeed, 20.0)
								MoveEntity n\Collider, 0, 0, -n\CurrSpeed * FPSfactor
								AnimateNPC(n, 488, 522, -n\CurrSpeed*30)
								Exit
							EndIf
						EndIf
					EndIf
				Next
				If Curr173\Idle = SCP173_STATIONARY And Curr173\ContainmentState = SCP173_NOMOVE Then
					Curr173\IdleTimer = Max(Curr173\IdleTimer-FPSfactor,0)
				EndIf
				n\Angle = EntityYaw(n\Collider) + DeltaYaw(n\obj,Curr173\obj)
				RotateEntity n\Collider, 0, CurveAngle(n\Angle, EntityYaw(n\Collider), 20.0), 0
				;If Curr173\IdleTimer = 0.0 Then
				;	For n2.NPCs = Each NPCs
				;		If n2\NPCtype = NPCtype173Box Then
				;			If EntityDistanceSquared(Curr173\Collider, n2\Collider) < PowTwo(1) Then
				;				Curr173\Idle = SCP173_CONTAIN
				;				PlayMTFSound(LoadTempSound("SFX\Character\MTF\173\Box"+Rand(1, 3)+".ogg"), n)
				;				n\State = MTF_FOLLOWPLAYER
				;				n\Target = Null
				;			EndIf
				;		EndIf
				;	Next
				;EndIf
				;[End Block]
			Case MTF_CONTACT
				;[Block]
				If n\Target <> Null And n\Target\HP > 0 Then
					CurrentNPCDist = EntityDistanceSquared(n\Collider, n\Target\Collider)
					If CurrentNPCDist < PowTwo(6) ;And EntityVisible(n\Collider, n\Target\Collider) Then
						n\CurrSpeed = 0.0
						n\Angle = EntityYaw(n\Collider) + DeltaYaw(n\obj,n\Target\obj)
						RotateEntity n\Collider, 0, CurveAngle(n\Angle, EntityYaw(n\Collider), 20.0), 0
						If n\Frame = 1383 Then
							If n\Reload = 0 Then
								n\SoundChn2 = PlaySound2(LoadTempSound("SFX\Guns\P90\shoot" + Rand(1,4) + ".ogg"), Camera, n\Collider, 35)
								pvt% = CreatePivot()
								
								RotateEntity(pvt, EntityPitch(n\Collider), EntityYaw(n\Collider), 0, True)
								PositionEntity(pvt, EntityX(n\obj), EntityY(n\obj), EntityZ(n\obj))
								MoveEntity (pvt,0.8*0.079, 10.0*0.079, 6.0*0.079)
								
								ShootTarget(EntityX(pvt), EntityY(pvt), EntityZ(pvt), n, Rnd(0.4,0.8))
								n\Reload = 7
							EndIf
						Else
							If n\Frame > 1383 Then
								SetNPCFrame(n, 1375)
							EndIf
							AnimateNPC(n,1375,1383,0.2,False)
						EndIf
					Else
						v3d_1 = CreateVector3D(79, 309, 0.3)
						v3d_2 = CreateVector3D(488, 522, 0.3)
						NPC_GoTo(n, v3d_1, v3d_2, n\Target\Collider, 0.7)
						Delete v3d_1
						Delete v3d_2
					EndIf
				ElseIf IsZombie And psp\Health > 0 Then
					n\Angle = EntityYaw(n\Collider) + DeltaYaw(n\obj,Collider)
					RotateEntity n\Collider, 0, CurveAngle(n\Angle, EntityYaw(n\Collider), 20.0), 0
					If n\Frame = 1383 Then
						If n\Reload = 0 Then
							n\SoundChn2 = PlaySound2(LoadTempSound("SFX\Guns\P90\shoot" + Rand(1,4) + ".ogg"), Camera, n\Collider, 35)
							pvt% = CreatePivot()
							
							RotateEntity(pvt, EntityPitch(n\Collider), EntityYaw(n\Collider), 0, True)
							PositionEntity(pvt, EntityX(n\obj), EntityY(n\obj), EntityZ(n\obj))
							MoveEntity (pvt,0.8*0.079, 10.0*0.079, 6.0*0.079)
							
							ShootPlayer(EntityX(pvt), EntityY(pvt), EntityZ(pvt), Rnd(0.4,0.8))
							n\Reload = 10
						EndIf
					Else
						If n\Frame > 1383 Then
							SetNPCFrame(n, 1375)
						EndIf
						AnimateNPC(n,1375,1383,0.2,False)
					EndIf
				Else
					pvt = FreeEntity_Strict(pvt)
					n\IdleTimer = 70*10
					n\State = MTF_SEARCH
					n\Target = Null
				EndIf
				;[End block]
			Case MTF_FLEE
				;[Block]
				If n\Target <> Null Then
					CurrentNPCDist = EntityDistanceSquared(n\Collider, n\Target\Collider)
				EndIf
				
				If n\Target <> Null And CurrentNPCDist < PowTwo(35) Then
					If CurrentNPCDist < PowTwo(2) Then
						n\CurrSpeed = CurveValue(n\Speed, n\CurrSpeed, 20.0)
						MoveEntity n\Collider, 0, 0, -n\CurrSpeed * FPSfactor
						n\Angle = EntityYaw(n\Collider) + DeltaYaw(n\obj,n\Target\obj)
						RotateEntity n\Collider, 0, CurveAngle(n\Angle, EntityYaw(n\Collider), 20.0), 0
						AnimateNPC(n,488,522,-0.5)
						n\Angle = EntityYaw(n\Collider) + DeltaYaw(n\obj,n\Target\obj)
						RotateEntity n\Collider, 0, CurveAngle(n\Angle, EntityYaw(n\Collider), 20.0), 0
						If n\Reload = 0 Then
							n\SoundChn2 = PlaySound2(LoadTempSound("SFX\Guns\P90\shoot"+Rand(1,4)+".ogg"), Camera, n\Collider, 35)
							ShootTarget(0, 0, 0, n, Rnd(0.4,0.8))
							n\Reload = 7
						EndIf
					Else
						v3d_1 = CreateVector3D(79, 309, 0.3)
						v3d_2 = CreateVector3D(488, 522, 0.3)
						NPC_GoToRoom(n, v3d_1, v3d_2, 0.7)
						Delete v3d_1
						Delete v3d_2
					EndIf
				Else
					n\State = MTF_FOLLOWPLAYER
				EndIf
				;[End block]
			Case MTF_SEARCH	
				;[Block]
				v3d_1 = CreateVector3D(79, 309, 0.3)
				v3d_2 = CreateVector3D(488, 522, 0.3)
				NPC_GoToRoom(n, v3d_1, v3d_2, 0.5)
				Delete v3d_1
				Delete v3d_2
				
				If n\IdleTimer = 0.0 Then
					n\State = MTF_FOLLOWPLAYER
				EndIf
				;[End Block]
			Case MTF_SPLIT_UP
				;[Block]
				For e = Each Events
					If e <> Null Then
						If e\EventName = "room2_tesla" Then
							If Abs(EntityX(n\Collider)-EntityX(e\room\obj,True))<4.0 Then
								If Abs(EntityZ(n\Collider)-EntityZ(e\room\obj,True))<4.0 Then
									If e\EventState2 <= 70*3.5 Then
										n\Idle = 70*10
										PlayMTFSound(LoadTempSound("SFX\Character\MTF\Tesla0.ogg"), n)
										e\EventState2 = 70*100
										e\EventState3=e\EventState3+140
										Exit
									EndIf
								EndIf
							EndIf
						EndIf
					EndIf
				Next
				v3d_1 = CreateVector3D(962, 1259, 0.3)
				v3d_2 = CreateVector3D(1496, 1524, 0.3)
				NPC_GoToRoom(n, v3d_1, v3d_2, 0.7)
				Delete v3d_1
				Delete v3d_2
				;[End block]
			Case MTF_TOTARGET
				;[Block]
				pvt = CreatePivot()
				PositionEntity pvt, n\EnemyX, n\EnemyY, n\EnemyZ
				If EntityDistanceSquared(n\Collider, pvt) < PowTwo(0.3) Then
					If n\CurrSpeed <= 0.01 Then
						n\CurrSpeed = 0.0
						AnimateNPC(n, 962, 1259, 0.3)
					Else
						n\CurrSpeed = CurveValue(0.0,n\CurrSpeed,20.0)
						AnimateNPC(n, 1496, 1524, n\CurrSpeed*30)
						MoveEntity n\Collider, 0, 0, n\CurrSpeed * FPSfactor
					EndIf
				Else
					n\Angle = EntityYaw(n\Collider) + DeltaYaw(n\obj,pvt)
					RotateEntity n\Collider, 0, CurveAngle(n\Angle, EntityYaw(n\Collider), 20.0), 0
					n\CurrSpeed = CurveValue(n\Speed*0.7, n\CurrSpeed, 20.0)
					AnimateNPC(n, 1496, 1524, n\CurrSpeed*30)
					MoveEntity n\Collider, 0, 0, n\CurrSpeed * FPSfactor
				EndIf
				pvt = FreeEntity_Strict(pvt)
				;[End Block]
			Case STATE_SCRIPT
				;[Block]
				
				;[End Block]
		End Select
		n\IdleTimer = Max(0.0, n\IdleTimer-FPSfactor)
	ElseIf n\HP = -1 Then
		EntityType n\Collider, HIT_DEAD
		If n\Frame > 21 Then
			SetNPCFrame(n, 27)
		Else
			AnimateNPC(n, 2, 27, 0.3)
		EndIf
		If n\SoundChn <> 0
			StopChannel n\SoundChn
			n\SoundChn = 0
			FreeSound_Strict n\Sound
			n\Sound = 0
		EndIf
	Else
		EntityType n\Collider, HIT_DEAD
		If n\Frame = 1407 Then
			SetNPCFrame(n, 1407)
		Else
			AnimateNPC(n, 1383, 1407, 0.05, False)
		EndIf
		If n\SoundChn <> 0
			StopChannel n\SoundChn
			n\SoundChn = 0
			FreeSound_Strict n\Sound
			n\Sound = 0
		EndIf
	EndIf
	
	;Play step sounds
	If n\CurrSpeed > 0.01 Then
		If prevFrame > 500 And n\Frame<495 Then
			sfxstep = GetStepSound(n\Collider,n\CollRadius)
			PlaySound2(mpl\StepSoundWalk[Rand(0,MaxStepSounds-1)+(sfxstep*MaxStepSounds)], Camera, n\Collider, 8.0, Rnd(0.5,0.7))
		ElseIf prevFrame < 505 And n\Frame=>505
			sfxstep = GetStepSound(n\Collider,n\CollRadius)
			PlaySound2(mpl\StepSoundWalk[Rand(0,MaxStepSounds-1)+(sfxstep*MaxStepSounds)], Camera, n\Collider, 8.0, Rnd(0.5,0.7))
		ElseIf prevFrame < 1509 And n\Frame=>1509
			sfxstep = GetStepSound(n\Collider,n\CollRadius)
			PlaySound2(mpl\StepSoundWalk[Rand(0,MaxStepSounds-1)+(sfxstep*MaxStepSounds)], Camera, n\Collider, 8.0, Rnd(0.5,0.7))
		ElseIf prevFrame < 1522 And n\Frame=>1522
			sfxstep = GetStepSound(n\Collider,n\CollRadius)
			PlaySound2(mpl\StepSoundWalk[Rand(0,MaxStepSounds-1)+(sfxstep*MaxStepSounds)], Camera, n\Collider, 8.0, Rnd(0.5,0.7))
		EndIf
	EndIf
	
;	If n\State = MTF_FOLLOWPLAYER Then
;		For e.Events = Each Events
;			If Abs(EntityX(n\Collider)-EntityX(e\room\obj,True))<4.0 Then
;				If Abs(EntityZ(n\Collider)-EntityZ(e\room\obj,True))<4.0 Then 
;					If e\EventName = "106_victim" Then
;						If e\EventState>200 Then
;							If Rand(2)=1 Then
;								If n\SayOnce = False Then
;									PlayMTFSound(LoadTempSound("SFX\Character\MTF\React1.ogg"), n)
;									n\SayOnce = True
;								EndIf
;							EndIf
;						EndIf
;					ElseIf e\EventName = "cont_914" Then
;						If Rand(2)=1 Then
;							If n\SayOnce = False Then
;								PlayMTFSound(LoadTempSound("SFX\Character\MTF\React2.ogg"), n)
;								n\SayOnce = True
;							EndIf
;						EndIf
;					EndIf
;				EndIf
;			EndIf
;		Next
;;		For r.Rooms = Each Rooms
;;			If Abs(EntityX(n\Collider)-EntityX(r\obj,True))<4.0 Then
;;				If Abs(EntityZ(n\Collider)-EntityZ(r\obj,True))<4.0 Then 
;;					If r\RoomTemplate\Name = "room2_upper_office" Then
;;						If n\SayOnce = False Then
;;							PlayMTFSound(LoadTempSound("SFX\Character\MTF\React3.ogg"), n)
;;							n\SayOnce = True
;;						EndIf
;;					EndIf
;;				EndIf
;;			EndIf
;;		Next
;	EndIf
	
	If n\State <> STATE_SCRIPT Then
		If n\State <> MTF_TOTARGET Then
			For n2.NPCs = Each NPCs
				If n2\NPCtype = NPCtypeMTF Then
					If n2\ID > n\ID Then
						If EntityDistanceSquared(n2\Collider,n\Collider)<PowTwo(0.5) Then
							TranslateEntity n2\Collider, Cos(EntityYaw(n2\Collider,True)+45)* n\Speed*0.7 * FPSfactor, 0, Sin(EntityYaw(n2\Collider,True)+45)* 0.005 * FPSfactor, True
						EndIf
					EndIf
				EndIf
			Next
		EndIf
		
		;Push MTF unit aside when player is close (only works in certain states)
		If IsPushable Then
			If PlayerDistSquared<PowTwo(0.7) Then
				n\Angle = EntityYaw(n\Collider) + DeltaYaw(n\obj,Collider)
				RotateEntity n\Collider,0.0,CurveAngle(n\Angle,EntityYaw(n\Collider,True),20.0),0.0,True
				TranslateEntity n\Collider, Cos(EntityYaw(n\Collider,True)-45)* 0.005 * FPSfactor, 0, Sin(EntityYaw(n\Collider,True)-45)* 0.005 * FPSfactor, True
			EndIf
			If Abs(DeltaYaw(Collider,n\Collider))<80.0 Then
				If PlayerDistSquared<PowTwo(0.7) Then
					TranslateEntity n\Collider, Cos(EntityYaw(Collider,True)+90)* 0.005 * FPSfactor, 0, Sin(EntityYaw(Collider,True)+90)* 0.005 * FPSfactor, True
				EndIf
			EndIf
		EndIf
	EndIf
	
	;Position the NPC's model (although not if the MTF unit is sitting in the helicopter)
	If Int(n\State)<>MTF_INTRO Lor Int(n\State2)<>0 Then
		RotateEntity n\obj,0.0,EntityYaw(n\Collider,True),0.0,True
		PositionEntity n\obj,EntityX(n\Collider,True),EntityY(n\Collider,True)-n\CollRadius,EntityZ(n\Collider,True),True
	EndIf
	
End Function





;~IDEal Editor Parameters:
;~C#Blitz3D