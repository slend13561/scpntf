
;Multiplayer Zombie constants
;[Block]
Const MPZ_STATE_FREEZE = -1
Const MPZ_STATE_WANDER = 0
Const MPZ_STATE_DETECTED = 1
Const MPZ_STATE_ATTACK = 2
;[End Block]

;"Normal" zombies
Function CreateNPCtypeZombieMP(n.NPCs,model%=-1)
	Local temp#
	
	n\Collider = CreatePivot()
	n\CollRadius = 0.15
	EntityRadius n\Collider, n\CollRadius, 0.2
	EntityType n\Collider, HIT_NPC_MP
	
	If n\obj = 0 Then
		If model = -1 Then
			Local random% = Rand(1,100)
			If random < 20 Then
				n\State3 = 0 ;Topless zombie
			ElseIf random >= 20 And random < 25 Then
				n\State3 = 1 ;Hazmatsuit zombie
			ElseIf random >= 25 And random < 45 Then
				n\State3 = 2 ;D-Class zombie
			ElseIf random >= 45 And random < 65 Then
				n\State3 = 5 ;Another D-Class zombie variant	
			ElseIf random >= 65 And random < 75 Then
				n\State3 = 6 ;Scientist zombie
			ElseIf random >= 75 And random < 80 Then
				n\State3 = 7 ;Janitor zombie
			ElseIf random >= 80 And random < 85 Then
				n\State3 = 3 ;Clerk zombie
			ElseIf random >= 85 And random < 95 Then
				n\State3 = 4 ;Surgeon zombie
			Else
				n\State3 = 8 ;Worker zombie
			EndIf
		Else
			n\State3 = model
		EndIf
		n\obj = CopyEntity(mp_I\ZombieModel[n\State3])
		
		temp# = 0.0135
		ScaleEntity n\obj, temp, temp, temp
		
		MeshCullBox (n\obj, -MeshWidth(n\obj), -MeshHeight(n\obj), -MeshDepth(n\obj), MeshWidth(n\obj)*2, MeshHeight(n\obj)*2, MeshDepth(n\obj)*2)
	EndIf
	
	n\NVName = "a zombie"
	n\Speed = (1.6 / 100.0)
	n\HP = 70
	n\PathTimer = 70*5
	
	CopyHitBoxes(n)
	
End Function

Function UpdateNPCtypeZombieMP(n.NPCs)
	Local fb.FuseBox, cmsg.ChatMSG
	Local dist#,prevFrame#,yaw#
	
	prevFrame = n\Frame
	
	Local it.Items, Random%, bone%
	
	If (Not n\IsDead)
		Select n\State
			Case MPZ_STATE_FREEZE ;Frozen in place
				;[Block]
				;do nothing
				;[End Block]
			Case MPZ_STATE_WANDER ;Wandering around
				;[Block]
				If n\PathStatus=1 Then
					While n\Path[n\PathLocation]=Null
						If n\PathLocation >= 19 Then
							n\PathLocation = 0 : n\PathStatus = 0 : n\PathTimer = 0.0 : Exit
						Else
							n\PathLocation = n\PathLocation + 1
						EndIf
					Wend
					If n\PathStatus=1 Then
						PointEntity n\obj, n\Path[n\PathLocation]\obj
						If mp_I\PlayState = GAME_SERVER Then RotateEntity n\Collider, 0, CurveAngle(EntityYaw(n\obj), EntityYaw(n\Collider), 20.0), 0
						
						If n\NPCtype = NPCtypeZombieMP Then
							AnimateNPC(n, 64, 93, n\CurrSpeed*30)
						Else
							AnimateNPC(n,194,254,n\CurrSpeed*60)
						EndIf
						n\CurrSpeed = CurveValue(n\Speed*0.7, n\CurrSpeed, 20.0)
						MoveEntity n\Collider, 0, 0, n\CurrSpeed * FPSfactor
						
						dist = EntityDistanceSquared(n\Collider,n\Path[n\PathLocation]\obj)
						If dist < 0.09 Then
							n\PathLocation = n\PathLocation + 1
						EndIf
					EndIf
				Else
					If n\PathTimer <= 0.0 Then
						n\EnemyX = EntityX(Players[n\ClosestPlayer]\Collider)
						n\EnemyY = EntityY(Players[n\ClosestPlayer]\Collider)
						n\EnemyZ = EntityZ(Players[n\ClosestPlayer]\Collider)
						n\PathStatus = FindPath(n,n\EnemyX,n\EnemyY,n\EnemyZ)
						
						If n\PathStatus = 1 Then
							If n\Path[1]<>Null Then
								If n\Path[2]=Null And EntityDistanceSquared(n\Path[1]\obj,n\Collider)<0.16 Then
									n\PathLocation = 0
									n\PathStatus = 0
								EndIf
							EndIf
							If n\Path[0]<>Null And n\Path[1]=Null Then
								n\PathLocation = 0
								n\PathStatus = 0
							EndIf
						EndIf
						
						If n\PathStatus<>1 Then
							n\PathTimer = 70*5
						EndIf
					Else
						n\PathTimer = n\PathTimer - FPSfactor
						
						PointEntity n\obj, Players[n\ClosestPlayer]\Collider
						If mp_I\PlayState = GAME_SERVER Then RotateEntity n\Collider, 0, CurveAngle(EntityYaw(n\obj), EntityYaw(n\Collider), 20.0), 0
						
						If n\NPCtype = NPCtypeZombieMP Then
							AnimateNPC(n,64,93,n\CurrSpeed*30)
						Else
							AnimateNPC(n,194,254,n\CurrSpeed*60)
						EndIf
						n\CurrSpeed = CurveValue(n\Speed*0.7, n\CurrSpeed, 20.0)
						MoveEntity n\Collider, 0, 0, n\CurrSpeed * FPSfactor
					EndIf
				EndIf
				
				If n\DistanceTimer <= 0.0 Then
					If EntityDistanceSquared(n\obj,Players[n\ClosestPlayer]\Collider)<56.25 Then
						If EntityVisible(n\obj,Players[n\ClosestPlayer]\Collider) Then
							n\State = MPZ_STATE_DETECTED
						EndIf
					EndIf
					n\DistanceTimer = NPCDistanceCheckTime
				Else
					n\DistanceTimer = n\DistanceTimer - FPSfactor
				EndIf
				
				If mp_I\Gamemode\EnemyCount <= MinEnemyLeft Then
					If n\BlinkTimer <= 0.0 Then
						CreateOverHereParticle(EntityX(n\Collider),EntityY(n\Collider)+0.5,EntityZ(n\Collider))
						n\BlinkTimer = 70*5
					Else
						n\BlinkTimer = n\BlinkTimer - FPSfactor
					EndIf
				EndIf
				;[End Block]
			Case MPZ_STATE_DETECTED ;Player detected
				;[Block]
				PointEntity n\obj, Players[n\ClosestPlayer]\Collider
				If mp_I\PlayState = GAME_SERVER Then RotateEntity n\Collider, 0, CurveAngle(EntityYaw(n\obj), EntityYaw(n\Collider), 20.0), 0
				
				If n\NPCtype = NPCtypeZombieMP Then
					AnimateNPC(n, 64, 93, n\CurrSpeed*30)
				Else
					AnimateNPC(n,194,254,n\CurrSpeed*60)
				EndIf
				n\CurrSpeed = CurveValue(n\Speed*0.7, n\CurrSpeed, 20.0)
				MoveEntity n\Collider, 0, 0, n\CurrSpeed * FPSfactor
				
				dist = EntityDistanceSquared(n\obj,Players[n\ClosestPlayer]\Collider)
				If dist<0.5625 Then
					If (Abs(DeltaYaw(n\Collider,Players[n\ClosestPlayer]\Collider))<=60.0) Then
						n\State = MPZ_STATE_ATTACK
						If n\NPCtype = NPCtypeGuardZombieMP Then
							If Rand(2)=1 Then
								SetNPCFrame(n,255)
							Else
								SetNPCFrame(n,306)
							EndIf
						EndIf
					EndIf
				EndIf
				
				If n\DistanceTimer <= 0.0 Then
					If dist>56.25 Lor (Not EntityVisible(n\obj,Players[n\ClosestPlayer]\Collider)) Then
						n\State = MPZ_STATE_WANDER
						n\PathTimer = 0.0
						n\PathStatus = 0
					EndIf
					n\DistanceTimer = NPCDistanceCheckTime
				Else
					n\DistanceTimer = n\DistanceTimer - FPSfactor
				EndIf
				;[End Block]
			Case MPZ_STATE_ATTACK ;Attacking
				;[Block]
				Local shouldFrame#
				Local restartFrame#
				Local finalFrame#
				If n\NPCtype = NPCtypeZombieMP Then
					AnimateNPC(n, 126, 165, 0.4, False)
					shouldFrame = 146
					restartFrame = 126
					finalFrame = 164.5
				Else
					If n\Frame < 306 Then
						AnimateNPC(n, 255, 305, 0.7, False)
						shouldFrame = 276
						finalFrame = 304.5
					Else
						AnimateNPC(n, 306, 356, 0.7, False)
						shouldFrame = 331
						finalFrame = 355.5
					EndIf
				EndIf
				dist = EntityDistanceSquared(n\Collider,Players[n\ClosestPlayer]\Collider)
				yaw = Abs(DeltaYaw(n\Collider,Players[n\ClosestPlayer]\Collider))
				If (dist<1.21) Then
					If (yaw<=60.0) Then
						If prevFrame < shouldFrame And n\Frame => shouldFrame Then
							PlaySound2(DamageSFX[Rand(5,8)],Camera,n\Collider)
							If n\ClosestPlayer = mp_I\PlayerID Then
								If Players[n\ClosestPlayer]\CurrKevlar>0 Then
									PlaySound_Strict(NTF_PainSFX[Rand(0,7)])
								Else
									PlaySound_Strict(NTF_PainWeakSFX[Rand(0,1)])
								EndIf
							Else
								If Players[n\ClosestPlayer]\CurrKevlar>0 Then
									PlaySound2(NTF_PainSFX[Rand(0,7)],Camera,Players[n\ClosestPlayer]\Collider)
								Else
									PlaySound2(NTF_PainWeakSFX[Rand(0,1)],Camera,Players[n\ClosestPlayer]\Collider)
								EndIf
							EndIf
							If Players[n\ClosestPlayer]\CurrHP > 0 Then
								DamagePlayer(n\ClosestPlayer,Rand(10,14),Rand(20,25),5)
								If Players[n\ClosestPlayer]\CurrHP <= 0 Then
									cmsg = AddChatMSG("death_killedby", 0, SERVER_MSG_IS, CHATMSG_TYPE_TWOPARAM_TRANSLATE)
									cmsg\Msg[1] = Players[n\ClosestPlayer]\Name
									cmsg\Msg[2] = n\NVName
									Players[n\ClosestPlayer]\Deaths = Players[n\ClosestPlayer]\Deaths + 1
								EndIf
							EndIf	
						EndIf
					EndIf
				EndIf
				If n\Frame => finalFrame Then
					If Players[n\ClosestPlayer]\Collider=0 Lor Players[n\ClosestPlayer]\CurrHP<=0 Then
						n\State = MPZ_STATE_WANDER
					Else
						If dist>1.0 Then
							n\State = MPZ_STATE_DETECTED
						EndIf
						If (yaw>60.0) Then
							n\State = MPZ_STATE_DETECTED
						EndIf
					EndIf
					If n\State = MPZ_STATE_ATTACK Then
						If n\NPCtype = NPCtypeGuardZombieMP Then
							If Rand(2)=1 Then
								restartFrame = 255
							Else
								restartFrame = 306
							EndIf
						EndIf
						SetNPCFrame(n,restartFrame)
					EndIf
				EndIf
				;[End Block]
		End Select
		
		If n\HP<=0 Then
			n\IsDead=True
			EntityType n\Collider,HIT_DEAD
			If n\NPCtype = NPCtypeZombieMP Then
				Select Rand(3)
					Case 1
						SetNPCFrame(n,204)
					Case 2
						SetNPCFrame(n,590)
					Case 3
						SetNPCFrame(n,642)
				End Select
			Else
				Select Rand(2)
					Case 1
						SetNPCFrame(n,357)
					Case 2
						SetNPCFrame(n,394)
				End Select		
			EndIf
			MoveEntity n\Collider,0,0.01,0
		EndIf
		If n\NPCtype = NPCtypeZombieMP Then
			;woops, not MTF cutie
		Else
			If n\State3 = 1 Then
				n\SoundChn = LoopSound2(n\Sound,n\SoundChn,Camera,n\Collider,5)
			EndIf	
		EndIf
	Else
		If n\SoundChn <> 0 Then
			StopChannel n\SoundChn
			n\SoundChn = 0
			FreeSound_Strict n\Sound
			n\Sound = 0
		EndIf
		Local dieFrame#
		If n\NPCtype = NPCtypeZombieMP Then
			If n\Frame < 642 Then
				If n\Frame < 590 Then
					AnimateNPC(n, 204, 349, 0.5, False)
					dieFrame = 348.5
				Else
					AnimateNPC(n, 590, 639, 0.5, False)
					dieFrame = 638.5
				EndIf	
			Else
				AnimateNPC(n, 642, 699, 0.5, False)
				dieFrame = 698.5
			EndIf
		Else
			If n\Frame < 394 Then
				AnimateNPC(n, 357, 393, 0.5, False)
				dieFrame = 392.5
			Else
				AnimateNPC(n, 394, 424, 0.5, False)
				dieFrame = 423.5
			EndIf	
		EndIf
		If n\Frame >= dieFrame
			If n\State2 < 70*5 Then
				n\State2 = n\State2 + FPSfactor
			Else
				If n\State2 >= 70*5 And n\State2 < 1000 Then
					n\State2 = 1000
				ElseIf n\State2 >= 1000 And n\State2 < 2000 Then
					EntityAlpha n\obj,Inverse((n\State2-1000.0)/1000.0)
					n\State2 = n\State2 + 2*FPSfactor
				Else
					RemoveNPC(n)
					Return
				EndIf
			EndIf
		EndIf
		If mp_I\PlayState = GAME_SERVER Then
			If n\NPCtype = NPCtypeZombieMP Then
				If n\State3 = 4 Lor n\State3 = 8 Then
					If prevFrame < 348.5 And n\Frame => 348.5 Lor prevFrame < 638.5 And n\Frame => 638.5 Lor prevFrame < 698.5 And n\Frame => 698.5 Then
						bone% = FindChild(n\obj,"Bip01_Spine1")
						If n\State3 = 4 Then
							Random = Rand(0,5)
							Select Random
								Case 0, 2, 4
								;nothing!
								Case 1
									it = CreateItem("First Aid Kit","firstaid",EntityX(bone%,True),EntityY(bone%,True)+0.025,EntityZ(bone%,True))
									EntityType it\collider, HIT_ITEM
									it\Dropped = 1
								Case 3
									it = CreateItem("Blue First Aid Kit","firstaid2",EntityX(bone%,True),EntityY(bone%,True)+0.025,EntityZ(bone%,True))
									EntityType it\collider, HIT_ITEM
									it\Dropped = 1
								Case 5
									it = CreateItem("Syringe","syringe",EntityX(bone%,True),EntityY(bone%,True)+0.025,EntityZ(bone%,True))
									EntityType it\collider, HIT_ITEM
									it\Dropped = 1
							End Select
						Else
							Local FusesAmount% = 0
							Local FusesActivatedAmount% = 0
							For fb = Each FuseBox
								FusesAmount% = FusesAmount + MaxFuseAmount
								FusesActivatedAmount = FusesActivatedAmount + fb\fuses
							Next
							If FusesActivatedAmount < FusesAmount Then
								it = CreateItem("Fuse","fuse",EntityX(bone%,True),EntityY(bone%,True)+0.025,EntityZ(bone%,True))
								EntityType it\collider, HIT_ITEM
								it\Dropped = 1
							EndIf
						EndIf
					EndIf
				EndIf	
			Else
				If prevFrame < 392.5 And n\Frame => 392.5 Lor prevFrame < 423.5 And n\Frame => 423.5 Then
					Random = Rand(0,1)
					Select Random
						Case 0
							;nothing!
						Case 1	
							bone% = FindChild(n\obj,"chest")
							it = CreateItem("Ammo Crate","ammocrate",EntityX(bone%,True),EntityY(bone%,True),EntityZ(bone%,True))
							EntityType it\collider, HIT_ITEM
							it\Dropped = 1
					End Select
				EndIf
			EndIf
		EndIf
	EndIf
	
	If n\NPCtype = NPCtypeZombieMP Then
		RotateEntity n\obj,0,EntityYaw(n\Collider)-180,0
	Else
		RotateEntity n\obj,-90,EntityYaw(n\Collider),0
	EndIf
	PositionEntity n\obj,EntityX(n\Collider),EntityY(n\Collider)-0.2,EntityZ(n\Collider)
	
	EntityAutoFade(n\obj,GetCameraFogRangeFar(Camera)-0.5,GetCameraFogRangeFar(Camera)+0.5)
	
End Function

;Guard zombies
Function CreateNPCtypeZombieMP_Guard(n.NPCs,model%=-1)
	Local temp#
	
	n\Collider = CreatePivot()
	n\CollRadius = 0.15
	EntityRadius n\Collider, n\CollRadius, 0.2
	EntityType n\Collider, HIT_NPC_MP
	
	If n\obj = 0 Then
		If model = -1 Then
			Local random% = Rand(1,100)
			If random < 80 Then
				n\State3 = 0
			Else
				n\State3 = 1
			EndIf
		Else
			n\State3 = model
		EndIf
		n\obj = CopyEntity(mp_I\GuardZombieModel[n\State3])
		
		temp# = (GetINIFloat("DATA\NPCs.ini", "SCP-049-2", "scale") / 2.5)
		ScaleEntity n\obj, temp, temp, temp
		
		MeshCullBox (n\obj, -MeshWidth(n\obj), -MeshHeight(n\obj), -MeshDepth(n\obj), MeshWidth(n\obj)*2, MeshHeight(n\obj)*2, MeshDepth(n\obj)*2)
	EndIf
	
	n\Speed = (GetINIFloat("DATA\NPCs.ini", "SCP-049-2", "speed") / 50.0)
	
	If n\State3 = 1 Then
		If n\Sound = 0 Then
			n\Sound = LoadSound_Strict("SFX\SCP\049\0492MTFBreath.ogg")
		EndIf
		n\NVName = "an NTF zombie"
		n\HP = 200
	Else
		n\NVName = "a guard zombie"
		n\HP = 150
	EndIf
	
	SetAnimTime(n\obj, 107)
	
	n\PathTimer = 70*5
	
	CopyHitBoxes(n)
	
End Function






;~IDEal Editor Parameters:
;~F#2#A#4B#68#6C#B9#DF#190
;~C#Blitz3D