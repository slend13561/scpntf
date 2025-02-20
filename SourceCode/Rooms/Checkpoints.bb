
Const CHECKPOINT_ELEVATOR_ID% = 0
Const CHECKPOINT_ELEVATOR_FAKE_DOOR_ID% = 1
Const CHECKPOINT_COUNTERWEIGHT_ID% = 2
Const CHECKPOINT_DARK_SPRITE_ID% = 3

Const CHECKPOINT_ELEVATOR_DOOR_ID% = 0
Const CHECKPOINT_AIRLOCK_DOOR1_ID% = 1
Const CHECKPOINT_AIRLOCK_DOOR2_ID% = 2

Function CreateCheckpointElevator(r.Rooms, ElevatorID%, ElevatorX#, ElevatorY#, ElevatorZ#, ElevatorYaw#, doorID%, DoorX#, DoorY#, DoorZ#, DoorYaw#)
	
	;Elevator mesh
	r\Objects[ElevatorID] = LoadRMesh("GFX\map\elevator_cabin.rmesh", Null)
	ScaleEntity(r\Objects[ElevatorID], RoomScale, RoomScale, RoomScale)
	EntityType(GetChild(r\Objects[ElevatorID], 2), HIT_MAP)
	EntityPickMode(GetChild(r\Objects[ElevatorID], 2), 2)
	PositionEntity(r\Objects[ElevatorID], r\x + ElevatorX * RoomScale, r\y + ElevatorY * RoomScale, r\z + ElevatorZ * RoomScale)
	RotateEntity(r\Objects[ElevatorID], 0, ElevatorYaw, 0)
	EntityParent(r\Objects[ElevatorID], r\obj)
	
	;Elevator door
	r\RoomDoors[doorID] = CreateDoor(r\zone, r\x + DoorX * RoomScale, r\y + DoorY * RoomScale, r\z + DoorZ * RoomScale, DoorYaw, r, True, 5)
	r\RoomDoors[doorID]\AutoClose = False
	r\RoomDoors[doorID]\DisableWaypoint = True
	MoveEntity(r\RoomDoors[doorID]\buttons[0], -25, 0, 2.5)
	
End Function

Function CreateCheckpointElevatorCounterWeight(r.Rooms, WeightID%, WeightX#, WeightY#, WeightZ#, WeightYaw#)
	
	;Weight object
	r\Objects[WeightID] = LoadRMesh("GFX\map\elevator_counterweight.rmesh", Null)
	ScaleEntity(r\Objects[WeightID], RoomScale, RoomScale, RoomScale)
	PositionEntity(r\Objects[WeightID], r\x + WeightX * RoomScale, r\y + WeightY * RoomScale, r\z + WeightZ * RoomScale)
	RotateEntity(r\Objects[WeightID], 0, WeightYaw, 0)
	EntityParent(r\Objects[WeightID], r\obj)
	
End Function

Function CreateCheckpointDarkSprite(r.Rooms, DarkSpriteID%)
	
	;Dark sprite
	r\Objects[DarkSpriteID] = CreateSprite(ark_blur_cam)
	ScaleSprite(r\Objects[DarkSpriteID], Max(opt\GraphicWidth / 1240.0, 1.0), Max(opt\GraphicHeight / 960.0 * 0.8, 0.8))
	EntityTexture(r\Objects[DarkSpriteID], DarkTexture)
	EntityBlend (r\Objects[DarkSpriteID], 1)
	EntityOrder r\Objects[DarkSpriteID], -1002
	MoveEntity(r\Objects[DarkSpriteID], 0, 0, 1.0)
	EntityAlpha r\Objects[DarkSpriteID], 0.0
	
End Function

Function CreateCheckpointFakeDoor(r.Rooms, FakeDoorID%, FakeDoorX#, FakeDoorY#, FakeDoorZ#, FakeDoorYaw#)
	
	;Fake Door
	r\Objects[FakeDoorID] = LoadMesh_Strict("GFX\map\doors\door_elevator_dummy.b3d")
	ScaleEntity(r\Objects[FakeDoorID], RoomScale, RoomScale, RoomScale)
	PositionEntity(r\Objects[FakeDoorID], r\x + FakeDoorX * RoomScale, r\y + FakeDoorY * RoomScale, r\z + FakeDoorZ * RoomScale)
	RotateEntity(r\Objects[FakeDoorID], 0, FakeDoorYaw, 0)
	EntityParent(r\Objects[FakeDoorID], r\obj)
	EntityType(r\Objects[FakeDoorID], HIT_MAP)
	HideEntity(r\Objects[FakeDoorID])
	
End Function

Function FillRoom_Checkpoints(r.Rooms)
	Local d.Doors
	Local i%
	
	;Airlock doors
	r\RoomDoors[CHECKPOINT_AIRLOCK_DOOR1_ID] = CreateDoor(r\zone,r\x,r\y,r\z - 400.0*RoomScale,180,r,True,DOOR_WINDOWED)
	r\RoomDoors[CHECKPOINT_AIRLOCK_DOOR2_ID] = CreateDoor(r\zone,r\x,r\y,r\z + 400.0*RoomScale,0,r,True,DOOR_WINDOWED)
	For i = 0 To 1
		r\RoomDoors[CHECKPOINT_AIRLOCK_DOOR1_ID]\buttons[i] = FreeEntity_Strict(r\RoomDoors[CHECKPOINT_AIRLOCK_DOOR1_ID]\buttons[i])
		r\RoomDoors[CHECKPOINT_AIRLOCK_DOOR2_ID]\buttons[i] = FreeEntity_Strict(r\RoomDoors[CHECKPOINT_AIRLOCK_DOOR2_ID]\buttons[i])
	Next
	
	;Elevator itself
	Select NTF_CurrZone
		Case LCZ
			CreateCheckpointElevator(r, CHECKPOINT_ELEVATOR_ID, 224, 0, 1926, 0, CHECKPOINT_ELEVATOR_DOOR_ID, 224, 0, 1644, 180)
			CreateCheckpointElevatorCounterWeight(r, CHECKPOINT_COUNTERWEIGHT_ID, 224, 0, 1926, 0)
			PositionEntity(r\RoomDoors[CHECKPOINT_ELEVATOR_DOOR_ID]\buttons[1], r\x, r\y + 0.7, r\z + 1604 * RoomScale, True)
			CreateNewElevator(r\Objects[CHECKPOINT_ELEVATOR_ID], 2, r\RoomDoors[CHECKPOINT_ELEVATOR_DOOR_ID], 1, r, -4096.0, 0.0, 4096.0)
			CreateCheckpointFakeDoor(r, CHECKPOINT_ELEVATOR_FAKE_DOOR_ID, 224, 0, 1644, 0)
		Case HCZ
			CreateCheckpointElevator(r, CHECKPOINT_ELEVATOR_ID, 224, 0, 2312, 0, CHECKPOINT_ELEVATOR_DOOR_ID, 224, 0, 2030, 180)
			PositionEntity(r\RoomDoors[CHECKPOINT_ELEVATOR_DOOR_ID]\buttons[1], r\x, r\y + 0.7, r\z + 1990 * RoomScale, True)
			CreateNewElevator(r\Objects[CHECKPOINT_ELEVATOR_ID], 1, r\RoomDoors[CHECKPOINT_ELEVATOR_DOOR_ID], 1, r, 0.0, 4096.0, 8192.0)
			CreateCheckpointFakeDoor(r, CHECKPOINT_ELEVATOR_FAKE_DOOR_ID, 224, 0, 2030, 0)
	End Select
	
	CreateCheckpointDarkSprite(r, CHECKPOINT_DARK_SPRITE_ID)
	
End Function

Function UpdateEvent_Checkpoints(e.Events)
	Local ne.NewElevator,r.Rooms,e2.Events
	Local playerElev%,prevZone%
	
	If PlayerRoom = e\room Then
		If e\room\Objects[CHECKPOINT_COUNTERWEIGHT_ID] <> 0 Then
			PositionEntity e\room\Objects[CHECKPOINT_COUNTERWEIGHT_ID],EntityX(e\room\Objects[CHECKPOINT_COUNTERWEIGHT_ID]),-EntityY(e\room\Objects[CHECKPOINT_ELEVATOR_ID]) - (7300 * (NTF_CurrZone = EZ)),EntityZ(e\room\Objects[CHECKPOINT_COUNTERWEIGHT_ID])
		EndIf
		If e\room\RoomDoors[CHECKPOINT_ELEVATOR_DOOR_ID]\openstate = 0.0 And (EntityHidden(e\room\Objects[CHECKPOINT_ELEVATOR_FAKE_DOOR_ID])) Then
			ShowEntity(e\room\Objects[CHECKPOINT_ELEVATOR_FAKE_DOOR_ID])
		ElseIf e\room\RoomDoors[CHECKPOINT_ELEVATOR_DOOR_ID]\openstate <> 0.0 And (Not EntityHidden(e\room\Objects[CHECKPOINT_ELEVATOR_FAKE_DOOR_ID])) Then
			HideEntity(e\room\Objects[CHECKPOINT_ELEVATOR_FAKE_DOOR_ID])
		EndIf
		If e\EventState2 = 0 And EntityY(Collider) > 2800.0*RoomScale Lor EntityY(Collider) <- 2800.0*RoomScale Then
			e\EventState = e\EventState + (0.01*FPSfactor)
			EntityAlpha e\room\Objects[CHECKPOINT_DARK_SPRITE_ID],Min(e\EventState,1.0)
			If e\EventState > 1.05 Then
				SaveGame(SavePath + CurrSave\Name + "\")
				prevZone = NTF_CurrZone
				For ne = Each NewElevator
					If PlayerNewElevator = ne\ID And ne\room = e\room Then
						Select ne\tofloor
							Case 3
								;SaveGame(SavePath + CurrSave\Name + "\", EZ)
								NTF_CurrZone = EZ
							Case 2
								;SaveGame(SavePath + CurrSave\Name + "\", LCZ)
								NTF_CurrZone = LCZ
							Case 1
								;SaveGame(SavePath + CurrSave\Name + "\", HCZ)
								NTF_CurrZone = HCZ
						End Select
						Exit
					EndIf
				Next
				If RandomSeed = "" Then
					RandomSeed = Abs(MilliSecs())
				EndIf
				SeedRnd GenerateSeedNumber(RandomSeed)
				ResetControllerSelections()
				DropSpeed = 0
				playerElev = PlayerNewElevator
				NullGame(True,False)
				LoadEntities()
				LoadAllSounds()
				Local zonecache% = NTF_CurrZone
				If FileType(SavePath + CurrSave\Name + "\" + NTF_CurrZone + ".ntf") = 1 Then
					LoadGame(SavePath + CurrSave\Name + "\", NTF_CurrZone)
					InitLoadGame()
				Else
					InitNewGame()
					LoadDataForZones(SavePath + CurrSave\Name + "\")
				EndIf
				NTF_CurrZone = zonecache
				MainMenuOpen = False
				FlushKeys()
				FlushMouse()
				FlushJoy()
				ResetInput()
				If PlayerRoom\RoomTemplate\Name = "gate_a_entrance" Then
					For r.Rooms = Each Rooms
						If r\RoomTemplate\Name = "checkpoint_ez" Then
							PlayerRoom = r
							UpdateRooms()
							UpdateDoors()
							Exit
						EndIf
					Next
				EndIf
				For ne = Each NewElevator
					If playerElev = ne\ID And ne\room = PlayerRoom Then
						PositionEntity ne\obj, EntityX(ne\obj), 0.0, EntityZ(ne\obj)
						Local translation# = 2700.0
						Select prevZone
							Case EZ
								TranslateEntity ne\obj, 0, translation, 0
							Case LCZ
								If NTF_CurrZone = EZ Then
									TranslateEntity ne\obj, 0, -translation, 0
								Else
									TranslateEntity ne\obj, 0, translation, 0
								EndIf
							Case HCZ
								TranslateEntity ne\obj, 0, -translation, 0
						End Select
						Select NTF_CurrZone
							Case EZ
								ne\tofloor = 3
								ne\currfloor = 2
							Case LCZ
								ne\tofloor = 2
								If prevZone = EZ Then
									ne\currfloor = 3
								Else
									ne\currfloor = 1
								EndIf
							Case HCZ
								ne\tofloor = 1
								ne\currfloor = 2
						End Select
						RotateEntity Collider,0,180,0
						TeleportEntity(Collider,EntityX(ne\obj,True),EntityY(ne\obj,True)+0.5,EntityZ(ne\obj,True),0.3,True)
						StopStream_Strict(ne\soundchn)
						ne\soundchn = StreamSound_Strict("SFX\General\Elevator\Loop.ogg",opt\SFXVolume,Mode)
						ne\currsound = 2
						ne\state = 200
						ne\door\open = False
						ne\door\openstate = 0.0
						PlayerInNewElevator = True
						PlayerNewElevator = ne\ID
						Exit
					EndIf
				Next
				For e2 = Each Events
					If e2\room = PlayerRoom Then
						e2\EventState = 1.05
						e2\EventState2 = 1
						Exit
					EndIf
				Next
				SaveGame(SavePath + CurrSave\Name + "\")
				Return
			EndIf
		Else
			e\EventState = Max(e\EventState - (0.01*FPSfactor), 0.0)
			EntityAlpha e\room\Objects[CHECKPOINT_DARK_SPRITE_ID],Min(e\EventState,1.0)
			If e\room\RoomDoors[CHECKPOINT_ELEVATOR_DOOR_ID]\open Then
				e\EventState2 = 0
			EndIf
		EndIf
	EndIf
	
End Function

;~IDEal Editor Parameters:
;~F#A#1D#28#35
;~C#Blitz3D