
;General constants
Const GAME_SERVER = 1
Const GAME_CLIENT = 2
Const UDP_MAX_BYTE_AMOUNT = 1200
Const MaxPlayers = 12

;Map constants
Const MaxMapLights = 128
Const MaxChunks = 8
Const MaxTriggers = 16

;Multiplayer gamemode constants
Const MaxGamemodeImages = 3
Const Gamemode_Waves = 0
Const Gamemode_Deathmatch = 1
Const Gamemode_Gungame = 2

;Waves constants
Const Waves_StartServer = 0
Const Waves_StartGame = 1
Const Waves_End = BYTE_MAX
Const Waves_Short = 4
Const Waves_Medium = 7
Const Waves_Long = 10

;NPC constants
Const NPCDistanceCheckTime# = 70*2
Const MaxZombieTypes = 8
Const MaxGuardZombieTypes = 2

;Deathmatch constants
Const Team_Unknown = -1
Const Team_Spectator = 0
Const Team_MTF = 1
Const Team_CI = 2
Const Deathmatch_GameStart = 0
Const Deathmatch_Game = 1
Const Deathmatch_MTFLost = 2
Const Deathmatch_CILost = 3
Const MaxPlayerTeams = 2

;Item spawner constants
Const ITEMTYPE_GUN = 0
Const ITEMTYPE_HEALTH = 1
Const ITEMTYPE_KEVLAR = 2
Const ITEMTYPE_AMMO = 3

;Packet constants
Const PACKET_PING = 0
Const PACKET_PLAYER = 1
Const PACKET_ITEM = 2
Const PACKET_NPC = 3
Const PACKET_EFFECT = 4
Const PACKET_QUIT = 5
Const PACKET_LOAD = 6
Const PACKET_KICK = 7
Const PACKET_LOBBY = 8
Const PACKET_AUTHORIZE = 9

;Leave message constants
Const KICK_QUIT$ = "The host quit the server."
Const KICK_TIMEOUT$ = "The server timed out."
Const KICK_KICK$ = "Kicked from the server."

;Multiplayer difficulty constants
Const MP_DIFFICULTY_SAFE = 0
Const MP_DIFFICULTY_EUCLID = 1
Const MP_DIFFICULTY_KETER = 2

;Player gunsound channel constants
Const MaxGunChannels = 2
Const GUN_CHANNEL_SHOT = 0
Const GUN_CHANNEL_OTHER = 1

Type Player
	Field IP%,Port%
	Field Name$
	Field Connected%,FinishedLoading%
	Field LastMsgTime
	Field IsReady%
	Field SendChatMSG$
	;Field CurrLoadPercent%
	Field CurrHP#
	Field CurrStamina#
	Field X#,Y#,Z#
	Field Pitch#,Yaw#
	Field DropSpeed#,CurrSpeed#
	Field Collider%,obj_lower%,obj_upper%
	Field walkangle#
	Field walking%
	Field Crouch%,CrouchState#
	Field IsPlayerSprinting%
	Field SelectedSlot%
	Field WantsSlot% ;Only for client!
	Field WeaponInSlot%[MaxSlots-1]
	Field Ammo%[MaxSlots-2] ;-2 as the melee slot doesn't need any ammo
	Field ReloadAmmo%[MaxSlots-2]
	Field PressMouse1
	Field PressReload
	Field PressSprint
	Field GunPivot
	Field ReloadState#
	Field ShootState#
	Field PrevShootState#
	Field DeployState#
	Field MuzzleFlashSprite%
	Field GunSFXChannel%[MaxGunChannels-1]
	Field IronSight%
	Field ShootAnim%
	Field CurrKevlar#
	Field Team%
	;Used to determine the ping
	Field Ping%
	Field LastPingMillisecs%
	Field PingCheckValue%
	;Name tags
	Field NameTag%
	Field CurrChunk%
	Field HitBox1[24]
	Field HitBox2[24]
	Field HitBox3[24]
	Field Kills%
	Field Deaths%
	Field GunModel%
	Field GunModelMuzzleFlash%
	Field AnimState_Lower%
	Field AnimState_Upper%
End Type

;Currently unused, will be added later sometime in either the Player Type or in a new type then - ENDSHN
;Field CurrDataPackage
;Field SpraySprite
;Field DataBank

Type MultiplayerInstance
	Field Server%
	Field Stream%
	Field PlayState%
	Field PlayerCount% ;Needed?
	Field PlayerID%
	Field PlayerName$
	Field ServerPort%
	Field Typename$ ;Needed?
	Field IsReady%
	Field ReadyTimer#
	Field TimeOut%
	Field MaxPlayers%
	Field CurrChatMSG$
	Field SendChatMSG$
	Field ShouldSendMSG%
	Field ChatMSGID%
	;Field CurrLoadPercent%
	Field PlayerModel_Lower[MaxPlayerTeams-1]
	Field PlayerModel_Upper[MaxPlayerTeams-1]
	Field Map.MultiplayerMap
	Field MapInList.MapForList
	Field Gamemode.MultiplayerGameMode
	Field HealthIcon
	Field ZombieModel[MaxZombieTypes-1]
	Field GuardZombieModel[MaxGuardZombieTypes-1]
	Field SCP939Model
	Field SCP1048aModel
	Field TentacleModel
	Field BossModel
	Field CurrSync% ;Needed?
	;Field SprayIMGPath$
	;Field EnableSprays%
	Field ConnectAddress$
	Field ConnectPort%
	Field SelectedServer$
	Field HasRefreshed%
	Field LocalServer%
	Field ServerName$
	Field ServerListPage%
	Field ServerListAmount%
	Field SelectedListServer%
	Field CurrNPCID% ;Needed?
	Field Offline%
	Field SpectatePlayer%
	Field PositionSyncTimer#
	Field PingTimer#
	Field ServerIcon%
	Field Password$
	Field ConnectPassword$
	Field PasswordVisible%
	Field PasswordIcon%
	Field BossNPC.NPCs
	Field MaxBossHealth%
	Field DeathChunk%
	Field MuzzleFlash%
End Type

Type ChatMSG
	Field txt$
	Field PlayerID%
	Field MsgID%
End Type

Type MultiplayerMap
	Field obj%
	Field LightAmount%
	Field Lights%[MaxMapLights]
	Field LightIntensity#[MaxMapLights]
	Field LightSprites%[MaxMapLights]
	Field LightSpriteHidden%[MaxMapLights]
	Field LightSpritesPivot%[MaxMapLights]
	Field LightSprites2%[MaxMapLights]
	Field LightHidden%[MaxMapLights]
	Field LightFlicker%[MaxMapLights]
	Field LightR#[MaxMapLights],LightG#[MaxMapLights],LightB#[MaxMapLights]
	Field Chunks%[MaxChunks]
	Field CurrChunk%
	Field Triggers%[MaxTriggers]
	Field TriggerPoint1%[MaxTriggers]
	Field TriggerPoint2%[MaxTriggers]
End Type

Type MapForList
	Field MeshPath$
	Field Name$
	Field Gamemodes$
	Field ChunkStart%, ChunkEnd%
	Field NTFSpawn%, CISpawn%
	Field TriggerAmount%
	Field TriggerMeshPath$
	Field TriggerYaw%[MaxTriggers]
	Field TriggerCoords.Vector3D[MaxTriggers]
	Field TriggerAreas.Vector2D[MaxTriggers]
	Field BossNPC$
End Type

Type MultiplayerGameMode
	Field name$
	Field ID%
	Field EnemyCount%
	Field PrevEnemyCount%
	Field Phase%
	Field PhaseTimer#
	Field img%[MaxGamemodeImages-1]
	Field MaxPhase%
	Field Difficulty%
End Type

Type MultiplayerMapTemplate
	Field obj%
End Type

Function InitMultiplayer()
	Local mp_I.MultiplayerInstance = New MultiplayerInstance
	Local gv.GlobalVariables = First GlobalVariables
	
	mp_I\PlayerName = GetINIString(gv\OptionFile,"client","username")
	mp_I\ServerPort = GetINIInt(gv\OptionFile,"server","port",13666)
	mp_I\MaxPlayers = GetINIInt(gv\OptionFile,"server","max players",4)
	mp_I\TimeOut = GetINIInt(gv\OptionFile,"server","timeout",5)
	;mp_I\SprayIMGPath$ = "Sprays\"+GetINIString(gv\OptionFile,"client","spray","")
	;mp_I\EnableSprays = False
	mp_I\ConnectAddress = GetINIString(gv\OptionFile,"client","direct connect IP","127.0.0.1")
	mp_I\ConnectPort = GetINIInt(gv\OptionFile,"client","direct connect port",13666)
	mp_I\LocalServer = GetINIInt(gv\OptionFile,"server","local",0)
	mp_I\ServerName = GetINIString(gv\OptionFile,"server","name")
	mp_I\Password = GetINIString(gv\OptionFile,"server","password")
	mp_I\SelectedServer = ""
	
	LoadMultiplayerMenuResources()
	
End Function

Function LoadMultiplayerMenuResources()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	mp_I\ServerIcon = LoadAnimImage("GFX\menu\server_icons.png",64,64,0,2)
	MaskImage mp_I\ServerIcon,0,0,0
	ResizeImage(mp_I\ServerIcon, (ImageWidth(mp_I\ServerIcon) * MenuScale)/2.4, (ImageHeight(mp_I\ServerIcon) * MenuScale)/2.4)
	
	mp_I\PasswordIcon = LoadAnimImage("GFX\menu\password_icons.png",30,30,0,2)
	MaskImage mp_I\PasswordIcon,0,0,0
	ResizeImage(mp_I\PasswordIcon, (ImageWidth(mp_I\PasswordIcon) * MenuScale), (ImageHeight(mp_I\PasswordIcon) * MenuScale))
	
End Function

InitMultiplayer()
LoadMPMaps()
LoadMPGameModes()

;Unused yet, so it's commented out
;[Block]
;Type Header
;	Field Characters%[9]
;End Type
;Global PNG.Header = New Header
;PNG\Characters[0] = $89
;PNG\Characters[1] = $50
;PNG\Characters[2] = $4E
;PNG\Characters[3] = $47
;PNG\Characters[4] = $0D
;PNG\Characters[5] = $0A
;PNG\Characters[6] = $1A
;PNG\Characters[7] = $0A
;Global JPG.Header = New Header
;JPG\Characters[0] = $FF
;JPG\Characters[1] = $D8
;JPG\Characters[2] = $FF
;JPG\Characters[3] = $E0
;JPG\Characters[4] = $00
;JPG\Characters[5] = $10
;JPG\Characters[6] = $4A
;JPG\Characters[7] = $46
;JPG\Characters[8] = $49
;JPG\Characters[9] = $46
;[End Block]

Function LoadMPMaps()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local gv.GlobalVariables = First GlobalVariables
	
	Local dir%, file$, dirPath$
	Local mfl.MapForList
	Local i%
	
	dirPath = "GFX\map\maps"
	dir = ReadDir(dirPath)
	Repeat 
		file$=NextFile$(dir)
		If file$="" Then Exit
		If FileType(dirPath+"\"+file$) = 2 Then
			If file <> "." And file <> ".." Then
				If (FileType(dirPath+"\"+file+"\config.ini")>0) Then
					mfl = New MapForList
					mfl\MeshPath = dirPath+"\"+file+"\"+GetINIString(dirPath+"\"+file+"\config.ini","global","mesh")
					mfl\Name = GetINIString(dirPath+"\"+file+"\config.ini","global","name")
					mfl\Gamemodes = GetINIString(dirPath+"\"+file+"\config.ini","global","gamemodes")
					mfl\ChunkStart = GetINIInt(dirPath+"\"+file+"\config.ini","chunkdata","start")
					mfl\ChunkEnd = GetINIInt(dirPath+"\"+file+"\config.ini","chunkdata","end")
					mfl\NTFSpawn = GetINIInt(dirPath+"\"+file+"\config.ini","chunkdata","ntf_spawn")
					mfl\CISpawn = GetINIInt(dirPath+"\"+file+"\config.ini","chunkdata","ci_spawn")
					mfl\TriggerAmount = GetINIInt(dirPath+"\"+file+"\config.ini","chunkdata","trigger_amount")
					mfl\TriggerMeshPath = dirPath+"\"+file+"\"+GetINIString(dirPath+"\"+file+"\config.ini","chunkdata","trigger_mesh")
					For i = 0 To mfl\TriggerAmount-1
						mfl\TriggerYaw[i] = GetINIInt(dirPath+"\"+file+"\config.ini","chunkdata","trigger"+(i+1)+"_yaw")
						Local x# = GetINIFloat(dirPath+"\"+file+"\config.ini","chunkdata","trigger"+(i+1)+"_x")
						Local y# = GetINIFloat(dirPath+"\"+file+"\config.ini","chunkdata","trigger"+(i+1)+"_y")
						Local z# = GetINIFloat(dirPath+"\"+file+"\config.ini","chunkdata","trigger"+(i+1)+"_z")
						Local area1 = GetINIInt(dirPath+"\"+file+"\config.ini","chunkdata","trigger"+(i+1)+"_area1")
						Local area2 = GetINIInt(dirPath+"\"+file+"\config.ini","chunkdata","trigger"+(i+1)+"_area2")
						mfl\TriggerCoords[i] = CreateVector3D(x,y,z,True)
						mfl\TriggerAreas[i] = CreateVector2D(area1,area2,True)
					Next
					mfl\BossNPC = GetINIString(dirPath+"\"+file+"\config.ini","global","boss")
					DebugLog mfl\Name+"|"+mfl\MeshPath
				EndIf
			EndIf
		EndIf
	Forever
	
	Local SelMap$ = GetINIString(gv\OptionFile,"server","map")
	If SelMap<>"" Then
		;Get the selected map, depending on what the player has selected
		For mfl = Each MapForList
			If mfl\Name = SelMap Then
				mp_I\MapInList = mfl
				Exit
			EndIf
		Next
	EndIf
	
	If mp_I\MapInList = Null Then
		;No name/entry found or no matching one found, just use the first map available in the list
		mp_I\MapInList = First MapForList
	EndIf
	
End Function

Function LoadMPGameModes()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local gv.GlobalVariables = First GlobalVariables
	
	Local mgm.MultiplayerGameMode
	
	;Gamemodes for multiplayer are hardcoded in the source code as there is no other way than using Blitz3D to code them - ENDSHN
	;Waves
	mgm = New MultiplayerGameMode
	mgm\ID = Gamemode_Waves
	mgm\name = "Waves"
	mgm\img[0] = LoadImage_Strict("GFX\skull_logo.png")
	MidHandle mgm\img[0]
	ResizeImage(mgm\img[0], ImageWidth(mgm\img[0]) * MenuScale, ImageHeight(mgm\img[0]) * MenuScale)
	mgm\MaxPhase = GetINIInt(gv\OptionFile,"server","waves_max",Waves_Short)
	mgm\Difficulty = GetINIInt(gv\OptionFile,"server","waves_difficulty",MP_DIFFICULTY_SAFE)
	;Deathmatch
	mgm = New MultiplayerGameMode
	mgm\ID = Gamemode_Deathmatch
	mgm\name = "Deathmatch"
	mgm\img[Team_MTF-1] = LoadImage_Strict("Loadingscreens\mtf.jpg")
	ResizeImage(mgm\img[Team_MTF-1], 300 * MenuScale, 300 * MenuScale)
	MaskImage mgm\img[Team_MTF-1],255,0,255
	mgm\img[Team_CI-1] = LoadImage_Strict("GFX\CI_logo.jpg")
	ResizeImage(mgm\img[Team_CI-1], 300 * MenuScale, 300 * MenuScale)
	MaskImage mgm\img[Team_CI-1],255,0,255
	;Use a better way in the future!
	mgm\img[2] = LoadImage_Strict("GFX\skull_logo.png")
	MidHandle mgm\img[2]
	ResizeImage(mgm\img[2], ImageWidth(mgm\img[2]) * MenuScale, ImageHeight(mgm\img[2]) * MenuScale)
	;Gungame
	mgm = New MultiplayerGameMode
	mgm\ID = Gamemode_Gungame
	mgm\name = "Gungame"
	
	Local SelGamemode$ = GetINIString(gv\OptionFile,"server","gamemode","")
	If SelGamemode$<>"" Then
		For mgm = Each MultiplayerGameMode
			If mgm\name = SelGamemode Then
				mp_I\Gamemode = mgm
				Exit
			EndIf
		Next
	EndIf
	
	If mp_I\Gamemode = Null Then
		mp_I\Gamemode = First MultiplayerGameMode
	EndIf
	
End Function

Function Connect(Addr$="",x%,y%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local fo.Fonts = First Fonts
	Local co.Controller = First Controller
	Local waitForCo% = MilliSecs()+10000
	Local j%
	Local disconnected = False
	Local mfl.MapForList, mgm.MultiplayerGameMode
	Local currMSGSync%
	Local ingame%, password%
	Local i%, exists%
	
	mp_I\Server% = CreateUDPStream() ;Create UDP Stream with any free port
	mp_I\Typename = "Connected"
	WriteByte mp_I\Server,PACKET_LOAD
	WriteLine mp_I\Server,mp_I\PlayerName
	CountHostIPs(Addr)
	SendUDPMsg(mp_I\Server,HostIP(1),mp_I\ConnectPort)
	mp_I\Stream = RecvUDPMsg(mp_I\Server)
	Local prevSendTime% = MilliSecs()
	While Not mp_I\Stream
		mp_I\Stream = RecvUDPMsg(mp_I\Server)
		MouseHit1 = MouseHit(1)
		DrawFrame(x,y,400*MenuScale,200*MenuScale)
		Color 255,255,255
		AASetFont fo\Font[Font_Default]
		AAText x+10*MenuScale,y+10*MenuScale,"Waiting... ("+Str(Int((MilliSecs()-(waitForCo-10000))/1000))+" second(s) have passed)"
		If DrawButton(x+150*MenuScale,y+160*MenuScale,100*MenuScale,30*MenuScale,"Cancel",False,False,True)
			DebugLog "Cancelled connection to server "+mp_I\Server+" Handle"
			Disconnect()
			disconnected = True
			DeleteMenuGadgets()
			Exit
		EndIf
		DrawAllMenuButtons()
		Flip
		Delay 10
		If MilliSecs()>prevSendTime+1000 Then
			WriteByte mp_I\Server,PACKET_LOAD
			WriteLine mp_I\Server,mp_I\PlayerName
			SendUDPMsg(mp_I\Server,HostIP(1),mp_I\ConnectPort)
			prevSendTime% = MilliSecs()
		EndIf
	Wend
	
	If disconnected Then Return
	Players(0)=New Player
	Players(0)\IP = UDPMsgIP(mp_I\Server)
	Players(0)\Port = UDPMsgPort(mp_I\Server) ;save the server's information
	Players(0)\LastMsgTime = MilliSecs()
	
	ConnectFinal()
	
End Function

Function ConnectFinal()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local mfl.MapForList,mgm.MultiplayerGameMode
	Local currMSGSync%,ingame%
	Local i%,exists%
	
	currMSGSync = ReadByte(mp_I\Server)
	
	If currMSGSync = PACKET_KICK Then
		CloseUDPStream(mp_I\Server)
		mp_I\PlayerCount = 0
		mp_I\Server = 0
		mp_I\ChatMSGID = 0
		Delete Each Player
		Players(0) = Null
		SaveMSG = KICK_KICK
		Return
	ElseIf currMSGSync = PACKET_AUTHORIZE Then
		SaveMSG = "Password"
		mp_I\ConnectPassword = ""
		mp_I\PasswordVisible = False
		ShouldDeleteGadgets = True
		Return
	Else
		mp_I\PlayerID = ReadByte(mp_I\Server)
		DebugLog "ID: "+mp_I\PlayerID
		mp_I\TimeOut = ReadInt(mp_I\Server)
		mp_I\MaxPlayers = ReadByte(mp_I\Server)
		Local mapName = ReadLine(mp_I\Server)
		For mfl = Each MapForList
			If mfl\Name = mapName Then
				mp_I\MapInList = mfl
				Exit
			EndIf
		Next
		Local gamemode = ReadByte(mp_I\Server)
		For mgm = Each MultiplayerGameMode
			If mgm\ID = gamemode Then
				mp_I\Gamemode = mgm
				Exit
			EndIf
		Next
		;Everything gamemode specific required for other clients to know will be received here
		Select mp_I\Gamemode\ID
			Case Gamemode_Waves
				;[Block]
				mp_I\Gamemode\MaxPhase = ReadByte(mp_I\Server)
				mp_I\Gamemode\Difficulty = ReadByte(mp_I\Server)
				;[End Block]
		End Select
		
		ingame = ReadByte(mp_I\Server)
	EndIf
	mp_I\PlayState=GAME_CLIENT
	
	If ingame Then
		mp_I\ReadyTimer = 0
		ResetControllerSelections()
		Null3DMenu()
		MainMenuOpen = False
		DebugLog "Starting Multiplayer Match!"
		For i = 1 To (mp_I\MaxPlayers)
			exists = ReadByte(mp_I\Server)
			If exists Then
				Players(i) = New Player
				Players(i)\Name = ReadLine(mp_I\Server)
			EndIf
		Next
		LoadingClient(True)
		NTF_GameModeFlag = 3
	Else
		mp_I\ReadyTimer = 70*5
	EndIf
	
End Function

Function CreateLobby()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	mp_I\Typename = "Hosting"
	mp_I\PlayState=GAME_SERVER
	mp_I\Server = CreateUDPStream(mp_I\ServerPort)
	DebugLog "---Created Server---"
	DebugLog "Handle: "+mp_I\Server
	;DebugLog "IP: "+mp_I\ServerAddress
	DebugLog "Port: "+mp_I\ServerPort
	DebugLog "Username: "+mp_I\PlayerName$
	Players(0)=New Player
	Players(0)\IP=0 ;there is no IP for this "client"
	Players(0)\Port=0 ;there is no port for this "client"
	Players(0)\Name = mp_I\PlayerName ;this is the host's name
	Players(0)\LastMsgTime = MilliSecs() ;this sets a timer to detect if the connection with the server is still established
	Players(0)\Connected = True
	mp_I\PlayerID = 0
	mp_I\PlayerCount = mp_I\PlayerCount + 1
	
End Function

Function CheckForConnectingPlayer(getconn%, currMSGSync%, ingame%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local giveID%, name$, password$, i%
	Local id% = -1
	
	For giveID=1 To mp_I\MaxPlayers
		If Players(giveID)<>Null Then
			If (Players(giveID)\IP = getconn) And (Players(giveID)\Port = UDPMsgPort(mp_I\Server))
				If Players(giveID)\Connected Then giveID = -1
				Exit
			EndIf
		EndIf
	Next
	If giveID>-1 Then
		For giveID=1 To mp_I\MaxPlayers
			If giveID >=mp_I\MaxPlayers Then giveID = -1 : Exit
			If (Players(giveID)=Null) Then Exit
		Next
	EndIf
	
	If currMSGSync = PACKET_AUTHORIZE Then
		password = ReadLine(mp_I\Server)
	EndIf
	name = ReadLine(mp_I\Server)
	
	If giveID<mp_I\MaxPlayers And giveID>0 Then ;server can accept another player
		If mp_I\Password = "" Lor (currMSGSync = PACKET_AUTHORIZE And password = mp_I\Password) Then
			If Players(giveID)=Null Then
				Players(giveID)=New Player
				Players(giveID)\IP=getconn
				Players(giveID)\Port=UDPMsgPort(mp_I\Server)
			EndIf
			Players(giveID)\LastMsgTime=MilliSecs()
			Players(giveID)\Name = name
			WriteByte(mp_I\Server,PACKET_LOAD)
			WriteByte(mp_I\Server,giveID) ;give the player their ID
			WriteInt(mp_I\Server,mp_I\TimeOut)
			WriteByte(mp_I\Server,mp_I\MaxPlayers)
			WriteLine(mp_I\Server,mp_I\MapInList\Name)
			WriteByte(mp_I\Server,mp_I\Gamemode\ID)
			
			;Everything gamemode specific required for other clients to know will be sent here
			Select mp_I\Gamemode\ID
				Case Gamemode_Waves
					;[Block]
					WriteByte(mp_I\Server,mp_I\Gamemode\MaxPhase)
					WriteByte(mp_I\Server,mp_I\Gamemode\Difficulty)
					;[End Block]
			End Select
			
			WriteByte(mp_I\Server,ingame) ;Tell the client that it is currently the lobby
			If ingame Then
				For i = 1 To (mp_I\MaxPlayers-1)
					If Players(i)<>Null Then
						WriteByte mp_I\Server,1
						WriteLine mp_I\Server,Players(i)\Name
					Else
						WriteByte mp_I\Server,0
					EndIf
				Next
			EndIf
			SendUDPMsg mp_I\Server,getconn,UDPMsgPort(mp_I\Server) ;give the message to the player
			mp_I\PlayerCount=mp_I\PlayerCount+1
			UpdateServer(mp_I\Gamemode\ID,mp_I\MapInList\Name,mp_I\PlayerCount)
			id = giveID
		ElseIf currMSGSync <> PACKET_AUTHORIZE Then
			WriteByte mp_I\Server,PACKET_AUTHORIZE
			SendUDPMsg mp_I\Server,getconn,UDPMsgPort(mp_I\Server)
		Else
			WriteByte mp_I\Server,PACKET_KICK ;kick the player
			SendUDPMsg mp_I\Server,getconn,UDPMsgPort(mp_I\Server) ;tell the client to disconnect
		EndIf
	Else ;server is full
		WriteByte mp_I\Server,PACKET_KICK ;kick the player
		SendUDPMsg mp_I\Server,getconn,UDPMsgPort(mp_I\Server) ;tell the client to disconnect
	EndIf
	
	Return id
	
End Function

Function UpdateLobby()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local getconn,giveID,TempID%,j%,i%
	Local mfl.MapForList
	
	Local password$, name$
	Local currMSGSync%
	
	getconn = RecvUDPMsg(mp_I\Server)
	While getconn ;The server has received a message from a client
		currMSGSync = ReadByte(mp_I\Server)
		Select currMSGSync
			Case PACKET_LOAD,PACKET_AUTHORIZE
				;[Block]
				CheckForConnectingPlayer(getconn, currMSGSync, 0)
				getconn = RecvUDPMsg(mp_I\Server)
				;[End Block]
			Case PACKET_QUIT
				;[Block]
				TempID%=ReadByte(mp_I\Server)
				If Players(TempID)<>Null Then ;this player exists: remove it
					If Players(TempID)\IP = getconn And Players(TempID)\Port = UDPMsgPort(mp_I\Server) Then
						mp_I\PlayerCount=mp_I\PlayerCount-1
						UpdateServer(mp_I\Gamemode\ID,mp_I\MapInList\Name,mp_I\PlayerCount)
						Delete Players(TempID)
						Players(TempID)=Null
					EndIf
				EndIf
				getconn = RecvUDPMsg(mp_I\Server)
				;[End Block]
			Case PACKET_LOBBY
				;[Block]
				TempID%=ReadByte(mp_I\Server)
				If Players(TempID)<>Null Then ;player exists
					If Players(TempID)\IP = getconn Then
						Players(TempID)\Connected = True
						Local ready% = ReadByte(mp_I\Server)
						Players(TempID)\IsReady = ready%
						Local hasSentMSG% = ReadByte(mp_I\Server)
						If hasSentMSG Then
							Local NewMSG$ = ReadLine(mp_I\Server)
							AddChatMSG(NewMSG,TempID)
							Players(TempID)\SendChatMSG = NewMSG
						EndIf
						Players(TempID)\LastMsgTime = MilliSecs()
					EndIf
				EndIf
				getconn = RecvUDPMsg(mp_I\Server)
				;[End Block]
		End Select
	Wend
	
	Local allPlayersReady% = True
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			If (Not Players(i)\IsReady) Then
				allPlayersReady = False
				Exit
			EndIf
		EndIf
	Next
	If allPlayersReady Then
		If mp_I\ReadyTimer > 0.0 Then
			mp_I\ReadyTimer = Max(mp_I\ReadyTimer-FPSfactor,0)
		Else
			mp_I\ReadyTimer = 0.0
		EndIf
	Else
		mp_I\ReadyTimer = 5*70
	EndIf
	
	For i=1 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			If Players(i)\Connected Then
				WriteByte mp_I\Server,PACKET_LOBBY
				For j=0 To (mp_I\MaxPlayers-1)
					If Players(j)<>Null Then
						WriteByte(mp_I\Server,j+1)
						WriteLine(mp_I\Server,Players(j)\Name)
						If j=0 Then
							WriteByte(mp_I\Server,mp_I\IsReady)
							If mp_I\SendChatMSG<>"" And mp_I\ShouldSendMSG Then
								WriteByte mp_I\Server,1
								WriteLine mp_I\Server,mp_I\SendChatMSG
							Else
								WriteByte mp_I\Server,0
							EndIf
						Else
							WriteByte(mp_I\Server,Players(j)\IsReady)
							If Players(j)\SendChatMSG<>"" And (j<>i) Then
								WriteByte mp_I\Server,1
								WriteLine mp_I\Server,Players(j)\SendChatMSG
							Else
								WriteByte mp_I\Server,0
							EndIf
						EndIf
					Else
						WriteByte(mp_I\Server,0)
					EndIf
				Next
				WriteByte(mp_I\Server,mp_I\PlayerCount)
				WriteFloat(mp_I\Server,mp_I\ReadyTimer)
				SendUDPMsg mp_I\Server,Players(i)\IP,Players(i)\Port
			EndIf
			
			If (MilliSecs()-Players(i)\LastMsgTime>(mp_I\TimeOut*1000)) Then ;remove client after X seconds of inactivity: assume connection was unexpectedly lost
				;MSG
				mp_I\PlayerCount=mp_I\PlayerCount-1
				UpdateServer(mp_I\Gamemode\ID,mp_I\MapInList\Name,mp_I\PlayerCount)
				Delete Players(i)
				Players(i)=Null
			EndIf
		EndIf
	Next
	mp_I\SendChatMSG = "" ;Reset chat message (from SERVER)
	For i=1 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			Players(i)\SendChatMSG = "" ;Reset chat message (from CLIENT)
		EndIf
	Next
	Players(0)\IsReady = mp_I\IsReady
	
End Function

Function UpdateLobbyClient()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local getconn,i,ttmp,ttmp2$,ready%,ttmp3%
	
	Local currMSGSync%
	
	Local allPlayersReady%
	getconn = RecvUDPMsg(mp_I\Server)
	While getconn ;the server has given you a message
		Players(0)\LastMsgTime = MilliSecs()
		currMSGSync = ReadByte(mp_I\Server)
		Select currMSGSync
			Case PACKET_KICK
				;[Block]
				For i=0 To (mp_I\MaxPlayers-1)
					If Players(i)<>Null Then
						Delete Players(i)
						Players(i)=Null
					EndIf
				Next
				Disconnect()
				MainMenuTab = MenuTab_Serverlist
				SaveMSG = KICK_KICK
				getconn = 0
				;[End Block]
			Case PACKET_QUIT
				;[Block]
				For i=0 To (mp_I\MaxPlayers-1)
					If Players(i)<>Null
						Delete Players(i)
						Players(i)=Null
					EndIf
				Next
				Disconnect()
				MainMenuTab = MenuTab_Serverlist
				SaveMSG = KICK_QUIT
				getconn = 0
				;[End Block]
			Case PACKET_LOBBY
				;[Block]
				For i=0 To (mp_I\MaxPlayers-1)
					ttmp% = ReadByte(mp_I\Server)
					If ttmp% Then
						ttmp2$ = ReadLine(mp_I\Server)
						If Players(i)=Null
							Players(i) = New Player
						EndIf
						Players(i)\Name = ttmp2
						ready = ReadByte(mp_I\Server)
						Players(i)\IsReady = ready
						Local hasSentMSG% = ReadByte(mp_I\Server)
						If hasSentMSG
							Local NewMSG$ = ReadLine(mp_I\Server)
							AddChatMSG(NewMSG,i)
						EndIf
					Else
						If Players(i)<>Null Then
							Delete Players(i)
							Players(i) = Null
						EndIf
					EndIf
				Next
				mp_I\PlayerCount = ReadByte(mp_I\Server)
				mp_I\ReadyTimer = ReadFloat(mp_I\Server)
				
				getconn = RecvUDPMsg(mp_I\Server)
				;[End Block]
		End Select
	Wend
	
	If mp_I\Server<>0 Then
		WriteByte mp_I\Server,PACKET_LOBBY
		WriteByte mp_I\Server,mp_I\PlayerID
		WriteByte mp_I\Server,mp_I\IsReady
		If mp_I\SendChatMSG<>"" And mp_I\ShouldSendMSG Then
			WriteByte mp_I\Server,1
			WriteLine mp_I\Server,mp_I\SendChatMSG
		Else
			WriteByte mp_I\Server,0
		EndIf
		;Reset chat message, so the chat won't be spammed with the same message automatically
		mp_I\SendChatMSG = ""
		SendUDPMsg(mp_I\Server,Players(0)\IP,Players(0)\Port)
		If (MilliSecs()-Players(0)\LastMsgTime>(mp_I\TimeOut*1000)) Then ;disconnect after X seconds of inactivity: assume connection was unexpectedly lost
			For i=0 To (mp_I\MaxPlayers-1)
				If Players(i)<>Null Then
					Delete Players(i)
					Players(i)=Null
				EndIf
			Next
			Disconnect()
			MainMenuTab = MenuTab_Serverlist
			SaveMSG = KICK_TIMEOUT
		EndIf
	EndIf
	
End Function

Function Disconnect()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local i%
	
	DebugLog "Disconnect Server "+mp_I\Server+" Handle"
	If mp_I\PlayerID = 0 Then
		For i=1 To (mp_I\MaxPlayers-1)
			If Players(i)<>Null Then
				WriteByte mp_I\Server,PACKET_QUIT
				SendUDPMsg(mp_I\Server,Players(i)\IP,Players(i)\Port)
			EndIf
		Next
	Else
		If Players(0)<>Null Then
			WriteByte mp_I\Server,PACKET_QUIT
			WriteByte(mp_I\Server,mp_I\PlayerID)
			SendUDPMsg(mp_I\Server,Players(0)\IP,Players(0)\Port)
		EndIf
	EndIf
	CloseUDPStream(mp_I\Server)
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			Delete Players(i)
		EndIf
	Next
	Delete Each ChatMSG
	mp_I\PlayerCount = 0
	mp_I\Server = 0
	mp_I\ChatMSGID = 0
	
End Function

Function CreateChatMSG()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local cmsg.ChatMSG
	
	mp_I\SendChatMSG = mp_I\CurrChatMSG
	mp_I\CurrChatMSG = ""
	CursorPos = 0
	If Left(mp_I\SendChatMSG,1)="/" ;Player entered a command (or at least tried)
		;Do nothing for now
		mp_I\ShouldSendMSG = False
	Else ;A message has been sent
		mp_I\ShouldSendMSG = True
		cmsg = New ChatMSG
		cmsg\PlayerID = mp_I\PlayerID
		cmsg\txt = mp_I\SendChatMSG
		cmsg\MsgID = mp_I\ChatMSGID
		mp_I\ChatMSGID = mp_I\ChatMSGID + 1
	EndIf
	
End Function

Function AddChatMSG(txt$,playerID%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local cmsg.ChatMSG
	
	cmsg = New ChatMSG
	cmsg\PlayerID = playerID
	cmsg\txt = "["+Players(playerID)\Name+"] "+txt
	cmsg\MsgID = mp_I\ChatMSGID
	mp_I\ChatMSGID = mp_I\ChatMSGID + 1
	
End Function

Function SaveMPOptions()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local gv.GlobalVariables = First GlobalVariables
	Local mgm.MultiplayerGameMode
	
	PutINIValue(gv\OptionFile,"client","username",mp_I\PlayerName)
	PutINIValue(gv\OptionFile,"server","port",mp_I\ServerPort)
	PutINIValue(gv\OptionFile,"server","max players",mp_I\MaxPlayers)
	PutINIValue(gv\OptionFile,"server","timeout",mp_I\TimeOut)
	;PutINIValue(gv\OptionFile,"server","enable sprays",mp_I\EnableSprays)
	PutINIValue(gv\OptionFile,"client","direct connect IP",mp_I\ConnectAddress)
	PutINIValue(gv\OptionFile,"client","direct connect port",mp_I\ConnectPort)
	PutINIValue(gv\OptionFile,"server","local",mp_I\LocalServer)
	PutINIValue(gv\OptionFile,"server","name",mp_I\ServerName)
	PutINIValue(gv\OptionFile,"server","password",mp_I\Password)
	PutINIValue(gv\OptionFile,"server","map",mp_I\MapInList\Name)
	PutINIValue(gv\OptionFile,"server","gamemode",mp_I\Gamemode\name)
	
	For mgm = Each MultiplayerGameMode
		Select mgm\ID
			Case Gamemode_Waves
				;[Block]
				PutINIValue(gv\OptionFile,"server","waves_difficulty",mgm\Difficulty)
				PutINIValue(gv\OptionFile,"server","waves_max",mgm\MaxPhase)
				;[End Block]
		End Select
	Next
	
End Function

Function LoadingServer()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local i%,getconn,TempRead$,ID%,j%,temp#
	Local mmt.MultiplayerMapTemplate
	
	DrawLoading(0)
	
	;Load the game for the server
	CreateMPGame()
	mmt = New MultiplayerMapTemplate
	mmt\obj = LoadRMesh(mp_I\MapInList\MeshPath+".rmesh",Null,False)
	mp_I\Map\obj = CopyEntity(mmt\obj)
	ScaleEntity mp_I\Map\obj,RoomScale,RoomScale,RoomScale
	EntityType mp_I\Map\obj,HIT_MAP
	EntityPickMode mp_I\Map\obj,2
	EntityPickMode GetChild(mp_I\Map\obj,RMESH_INVISBLE),2
	If mp_I\MapInList\ChunkEnd > 0 Then
		For i = mp_I\MapInList\ChunkStart To mp_I\MapInList\ChunkEnd
			mmt = New MultiplayerMapTemplate
			mmt\obj = LoadRMesh(mp_I\MapInList\MeshPath+"_chunk"+i+".rmesh",Null,False)
			mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart] = CopyEntity(mmt\obj)
			ScaleEntity mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart],RoomScale,RoomScale,RoomScale
			EntityType mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart],HIT_MAP
			EntityPickMode mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart],2
			EntityPickMode GetChild(mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart],RMESH_INVISBLE),2
		Next
	EndIf
	For i = 0 To mp_I\MapInList\TriggerAmount-1
		If i = 0 Then
			mp_I\Map\Triggers[i] = LoadMesh_Strict(mp_I\MapInList\TriggerMeshPath)
		Else
			mp_I\Map\Triggers[i] = CopyEntity(mp_I\Map\Triggers[0])
		EndIf
		ScaleEntity mp_I\Map\Triggers[i],RoomScale,RoomScale,RoomScale
		PositionEntity mp_I\Map\Triggers[i],mp_I\MapInList\TriggerCoords[i]\x*RoomScale,mp_I\MapInList\TriggerCoords[i]\y*RoomScale,mp_I\MapInList\TriggerCoords[i]\z*RoomScale
		RotateEntity mp_I\Map\Triggers[i],0,mp_I\MapInList\TriggerYaw[i],0
		EntityAlpha mp_I\Map\Triggers[i],0.0
		mp_I\Map\TriggerPoint1[i] = CreatePivot(mp_I\Map\Triggers[i])
		PositionEntity mp_I\Map\TriggerPoint1[i],0,0,-100
		mp_I\Map\TriggerPoint2[i] = CreatePivot(mp_I\Map\Triggers[i])
		PositionEntity mp_I\Map\TriggerPoint2[i],0,0,100
	Next
	
	Local tfll.TempFluLight,fll.FluLight
	For tfll.TempFluLight = Each TempFluLight
		fll = CreateFluLight(tfll\id)
		PositionEntity fll\obj,tfll\position\x,tfll\position\y,tfll\position\z
		RotateEntity fll\obj,tfll\rotation\x,tfll\rotation\y,tfll\rotation\z
		EntityPickMode fll\obj,2
		;EntityParent fll\obj,r\obj
		PositionEntity fll\lightobj,tfll\position\x,tfll\position\y,tfll\position\z
		EntityParent fll\lightobj,fll\obj
		PositionEntity fll\flashsprite,tfll\position\x,tfll\position\y-0.07,tfll\position\z
		EntityParent fll\flashsprite,fll\obj
	Next
	
	Select mp_I\MapInList\Name
        Case "Offices"
            InitFluLight(0,FLU_STATE_OFF,Null)
            InitFluLight(1,FLU_STATE_ON,Null)
            InitFluLight(2,FLU_STATE_FLICKER,Null)
    End Select
	
	DrawLoading(30)
	InitMPWayPoints(30)
	DrawLoading(70)
	
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			CreatePlayer(i)
			If Players(i)\Team > Team_Spectator Then
				SwitchPlayerGun(i)
			EndIf
		EndIf
	Next
	If Players(0)\Team > Team_Spectator Then
		EntityAlpha Players(0)\obj_lower,0.0
		EntityAlpha Players(0)\obj_upper,0.0
	EndIf
	
	If mp_I\Gamemode\ID = Gamemode_Deathmatch Then
		ApplyPlayerHitBoxes()
	EndIf
	
	ApplyHitBoxes(NPCtypeZombieMP,"Zombie2")
	ApplyHitBoxes(NPCtypeGuardZombieMP,"Zombie")
	ApplyHitBoxes(NPCtype1048a,"1048A")
	ApplyHitBoxes(NPCtype939,"939")
	ApplyHitBoxes(NPCtype035,"Class-D")
	ApplyHitBoxes(NPCtypeTentacle,"Tentacle")
	
	InitMPGame()
	RespawnPlayers()
	
	DrawLoading(100)
	
	For i = 1 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			Players(i)\LastMsgTime = MilliSecs()
		EndIf
	Next
	
	ResetTimingAccumulator()
	
End Function

Function LoadingClient(ingame%=False)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local getconn,TempRead$,Ready%,i%,temp#
	Local mmt.MultiplayerMapTemplate
	
	DrawLoading(0)
	
	;Create the game
	CreateMPGame()
	mmt = New MultiplayerMapTemplate
	mmt\obj = LoadRMesh(mp_I\MapInList\MeshPath+".rmesh",Null,False)
	mp_I\Map\obj = CopyEntity(mmt\obj)
	ScaleEntity mp_I\Map\obj,RoomScale,RoomScale,RoomScale
	EntityType mp_I\Map\obj,HIT_MAP
	EntityPickMode mp_I\Map\obj,2
	EntityPickMode GetChild(mp_I\Map\obj,RMESH_INVISBLE),2
	If mp_I\MapInList\ChunkEnd > 0 Then
		For i = mp_I\MapInList\ChunkStart To mp_I\MapInList\ChunkEnd
			mmt = New MultiplayerMapTemplate
			mmt\obj = LoadRMesh(mp_I\MapInList\MeshPath+"_chunk"+i+".rmesh",Null,False)
			mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart] = CopyEntity(mmt\obj)
			ScaleEntity mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart],RoomScale,RoomScale,RoomScale
			EntityType mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart],HIT_MAP
			EntityPickMode mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart],2
			EntityPickMode GetChild(mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart],RMESH_INVISBLE),2
		Next
	EndIf
	For i = 0 To mp_I\MapInList\TriggerAmount-1
		If i = 0 Then
			mp_I\Map\Triggers[i] = LoadMesh_Strict(mp_I\MapInList\TriggerMeshPath)
		Else
			mp_I\Map\Triggers[i] = CopyEntity(mp_I\Map\Triggers[0])
		EndIf
		ScaleEntity mp_I\Map\Triggers[i],RoomScale,RoomScale,RoomScale
		PositionEntity mp_I\Map\Triggers[i],mp_I\MapInList\TriggerCoords[i]\x*RoomScale,mp_I\MapInList\TriggerCoords[i]\y*RoomScale,mp_I\MapInList\TriggerCoords[i]\z*RoomScale
		RotateEntity mp_I\Map\Triggers[i],0,mp_I\MapInList\TriggerYaw[i],0
		EntityAlpha mp_I\Map\Triggers[i],0.0
		mp_I\Map\TriggerPoint1[i] = CreatePivot(mp_I\Map\Triggers[i])
		PositionEntity mp_I\Map\TriggerPoint1[i],0,0,-100
		mp_I\Map\TriggerPoint2[i] = CreatePivot(mp_I\Map\Triggers[i])
		PositionEntity mp_I\Map\TriggerPoint2[i],0,0,100
	Next
	
	Local tfll.TempFluLight,fll.FluLight
	For tfll.TempFluLight = Each TempFluLight
		fll = CreateFluLight(tfll\id)
		PositionEntity fll\obj,tfll\position\x,tfll\position\y,tfll\position\z
		RotateEntity fll\obj,tfll\rotation\x,tfll\rotation\y,tfll\rotation\z
		EntityPickMode fll\obj,2
		;EntityParent fll\obj,r\obj
		PositionEntity fll\lightobj,tfll\position\x,tfll\position\y,tfll\position\z
		EntityParent fll\lightobj,fll\obj
		PositionEntity fll\flashsprite,tfll\position\x,tfll\position\y-0.07,tfll\position\z
		EntityParent fll\flashsprite,fll\obj
	Next
	
	Select mp_I\MapInList\Name
        Case "Offices"
            InitFluLight(0,FLU_STATE_OFF,Null)
            InitFluLight(1,FLU_STATE_ON,Null)
            InitFluLight(2,FLU_STATE_FLICKER,Null)
    End Select
	
	DrawLoading(30)
	InitMPWayPoints(30)
	DrawLoading(70)
	
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			CreatePlayer(i)
			If Players(i)\Team > Team_Spectator Then
				SwitchPlayerGun(i)
			EndIf
			Players(i)\CurrHP = 100
		EndIf
	Next
	;Players(mp_I\PlayerID)\SelectedSlot = Slot_Secondary
	Players(mp_I\PlayerID)\WantsSlot = SLOT_SECONDARY
	If Players(0)\Team > Team_Spectator Then
		EntityAlpha Players(mp_I\PlayerID)\obj_lower,0.0
		EntityAlpha Players(mp_I\PlayerID)\obj_upper,0.0
	EndIf
	
	If mp_I\Gamemode\ID = Gamemode_Deathmatch Then
		Players(mp_I\PlayerID)\WantsSlot = SLOT_PRIMARY
	EndIf
	
	InitMPGame()
	
	;Now also check and wait until every player joined
	Local check%
	
	If (Not ingame) Then
		Repeat
			getconn = RecvUDPMsg(mp_I\Server)
			While getconn
				check = ReadByte(mp_I\Server)
				getconn = RecvUDPMsg(mp_I\Server)
			Wend
			
			If check Then
				Exit
			Else
				;Tell the server that you are ready
				WriteByte mp_I\Server,1
				WriteByte mp_I\Server,mp_I\PlayerID
				SendUDPMsg mp_I\Server,Players(0)\IP,Players(0)\Port
				AAText(opt\GraphicWidth / 2, opt\GraphicHeight - 50, "WAITING FOR THE HOST'S RESPONSE", True, True)
			EndIf
			
			Flip 1
		Forever
	EndIf
	
	DrawLoading(100)
	
	ResetTimingAccumulator()
	
End Function

Function CreatePlayer(playerID%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local temp#
	
	Players(playerID)\Collider = CreatePivot()
	EntityRadius Players(playerID)\Collider,0.15,0.30
	EntityType Players(playerID)\Collider,HIT_PLAYER_MP
	
	If Players(playerID)\Team > Team_Spectator Then
		ChangePlayerModel(playerID, Team_MTF-1)
		
		If playerID<>mp_I\PlayerID Then
			Local bonename$ = "hand_R"
			If bonename <> "" Then
				Local bone% = FindChild(Players(playerID)\obj_upper,bonename$)
			EndIf
		EndIf
		
		If mp_I\PlayState = GAME_SERVER Then
			Players(playerID)\CurrHP = 100
			Players(playerID)\CurrKevlar = 100
			;Players(playerID)\GunPivot = CreatePivot()
			Players(playerID)\CurrStamina = 100.0
			Players(playerID)\SelectedSlot = SLOT_SECONDARY
			Players(playerID)\WeaponInSlot[Players(playerID)\SelectedSlot] = GUN_USP
			Players(playerID)\WeaponInSlot[SLOT_MELEE] = GUN_KNIFE
			Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = GetWeaponMaxCurrAmmo(Players(playerID)\WeaponInSlot[Players(playerID)\SelectedSlot])
			Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot] = 3
			
			If mp_I\Gamemode\ID = Gamemode_Deathmatch Then
				Players(playerID)\SelectedSlot = SLOT_PRIMARY
				Players(playerID)\WeaponInSlot[Players(playerID)\SelectedSlot] = GUN_P90
				Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = GetWeaponMaxCurrAmmo(Players(playerID)\WeaponInSlot[Players(playerID)\SelectedSlot])
				Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot] = 3
			EndIf
		EndIf
		
		;SwitchPlayerGun(playerID)
	EndIf
	
	Players(playerID)\GunPivot = CreatePivot()
	
	CreatePlayerTag(playerID)
	
End Function

Function CreatePlayerTag(playerID%)
	CatchErrors("CreatePlayerTag(" + playerID + ")")
	Local fo.Fonts = First Fonts
	
;	Players(playerID)\NameTag = CreateSprite()
;	SpriteViewMode Players(playerID)\NameTag,1
;	AASetFont fo\Font[Font_Default_Large]
;	ScaleSprite Players(playerID)\NameTag,0.0006*(AAStringWidth(Players(playerID)\Name)),0.025
;	EntityFX Players(playerID)\NameTag,1+8
;	EntityOrder Players(playerID)\NameTag,-1
;	Local tex% = CreateTexture(1024,64)
;	SetBuffer TextureBuffer(tex)
;	ClsColor 0,0,0
;	Cls
;	Color 255,255,255
;	AAText(1,0,Players(playerID)\Name,False,False)
;	SetBuffer BackBuffer()
;	ScaleTexture tex,1024.0/AAStringWidth(Players(playerID)\Name),1.4
;	PositionTexture tex,0.0,0.01
;	EntityTexture Players(playerID)\NameTag,tex
;	DeleteSingleTextureEntryFromCache tex ;TODO I replaced this, because of similar coding causing hard to diagnose crashes with Debugger enabled, I have no idea how multiplayer works, if FreeTexture works fine here instead, revert this
	
	CatchErrors("Uncaught (CreatePlayerTag(" + playerID + "))")
End Function

Function SwitchPlayerGun(playerID%)
	CatchErrors("SwitchPlayerGun(" + playerID + ")")
	
	Local g.Guns
	Local VectorString$
	Local bonename$
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	If Players(playerID)\Team <= Team_Spectator Then
		CatchErrors("Uncaught (SwitchPlayerGun(" + playerID + "))")
		Return
	EndIf
	
	If Players(playerID)\GunModel <> 0 Then
		EntityParent Players(playerID)\GunModel,0
		FreeEntity Players(playerID)\GunModelMuzzleFlash
		FreeEntity Players(playerID)\GunModel
		Players(playerID)\GunModel = 0
		Players(playerID)\GunModelMuzzleFlash = 0
	EndIf
	
	Local prevYaw# = EntityYaw(Players(playerID)\obj_lower)
	Local prevX# = EntityX(Players(playerID)\obj_lower)
	Local prevY# = EntityY(Players(playerID)\obj_lower)
	Local prevZ# = EntityZ(Players(playerID)\obj_lower)
	;Local prevAnim# = AnimTime(Players(playerID)\obj_lower)
	;Local prevAnimUpper# = AnimTime(Players(playerID)\obj_upper)
	
	RotateEntity Players(playerID)\obj_lower,0,0,0
	PositionEntity Players(playerID)\obj_lower,0,0,0
	;SetAnimTime Players(playerID)\obj_lower,0
	;SetAnimTime Players(playerID)\obj_upper,0
	
	Local bone = FindChild(Players(playerID)\obj_upper,GetINIString("Data\PlayerBones.ini", "Player", "weapon_hand_bonename"))
	
	Local gunname$ = ""
	For g = Each Guns
		If g\ID = Players(playerID)\WeaponInSlot[Players(playerID)\SelectedSlot] Then
			Players(playerID)\GunModel = CopyEntity(g\PlayerModel,bone)
			gunname = g\name
			Exit
		EndIf
	Next
	
	If gunname <> "" Then
		Local scale# = GetINIFloat("Data\weapons.ini",gunname,"world scale",0.02) / (0.29 / 2.5)
		ScaleEntity Players(playerID)\GunModel,scale,scale,scale
		
		VectorString = GetINIString("Data\weapons.ini",gunname,"player_model_offset","")
		If VectorString<>"" Then
			PositionEntity Players(playerID)\GunModel,Piece(VectorString,1,"|"),Piece(VectorString,2,"|"),Piece(VectorString,3,"|")
		EndIf
		VectorString = GetINIString("Data\weapons.ini",gunname,"player_model_rotation","")
		If VectorString<>"" Then
			RotateEntity Players(playerID)\GunModel,Piece(VectorString,1,"|"),Piece(VectorString,2,"|"),Piece(VectorString,3,"|")
		EndIf
		
		bone = FindChild(Players(playerID)\GunModel,"Muzzle")
		Players(playerID)\GunModelMuzzleFlash = CopyEntity(mp_I\MuzzleFlash,bone)
		SpriteViewMode Players(playerID)\GunModelMuzzleFlash,3
		EntityFX Players(playerID)\GunModelMuzzleFlash,1
		HideEntity Players(playerID)\GunModelMuzzleFlash
		
		If playerID = mp_I\PlayerID Then
			HideEntity Players(playerID)\GunModel
		EndIf
	EndIf
	
	RotateEntity Players(playerID)\obj_lower,0,prevYaw,0
	PositionEntity Players(playerID)\obj_lower,prevX,prevY,prevZ
	;SetAnimTime Players(playerID)\obj_lower,prevAnim
	;SetAnimTime Players(playerID)\obj_upper,prevAnimUpper
	
	If mp_I\PlayerID<>playerID Then
		If Players(playerID)\CurrHP > 0 Then
			bonename$ = "chest"
			If bonename <> "" Then
				bone% = FindChild(Players(playerID)\obj_upper,bonename$)
				If bone <> 0 Then
					RotateEntity bone%,EntityPitch(bone),-Players(playerID)\Pitch,EntityRoll(bone)
				EndIf
			EndIf
		EndIf
	EndIf
	
	CatchErrors("Uncaught (SwitchPlayerGun(" + playerID + "))")
End Function

Function AddLightMPMap(mpmap.MultiplayerMap,x#,y#,z#,ltype%,range#,r%,g%,b%)
	Local i
	
	mpmap\Lights[mpmap\LightAmount] = CreateLight(ltype)
	LightRange(mpmap\Lights[mpmap\LightAmount],range)
	LightColor(mpmap\Lights[mpmap\LightAmount],r,g,b)
	PositionEntity(mpmap\Lights[mpmap\LightAmount],x,y,z,True)
	
	mpmap\LightIntensity[mpmap\LightAmount] = (r+g+b)/255.0/3.0
	
	mpmap\LightSprites[mpmap\LightAmount] = CreateSprite()
	PositionEntity(mpmap\LightSprites[mpmap\LightAmount], x, y, z)
	ScaleSprite(mpmap\LightSprites[mpmap\LightAmount], 0.13 , 0.13)
	EntityTexture(mpmap\LightSprites[mpmap\LightAmount], LightSpriteTex(0))
	EntityBlend (mpmap\LightSprites[mpmap\LightAmount], 3)
	
	mpmap\LightSpritesPivot[mpmap\LightAmount] = CreatePivot()
	EntityRadius mpmap\LightSpritesPivot[mpmap\LightAmount],0.05
	PositionEntity(mpmap\LightSpritesPivot[mpmap\LightAmount], x, y, z)
	
	mpmap\LightSprites2[mpmap\LightAmount] = CreateSprite()
	PositionEntity(mpmap\LightSprites2[mpmap\LightAmount], x, y, z)
	ScaleSprite(mpmap\LightSprites2[mpmap\LightAmount], 0.6, 0.6)
	EntityTexture(mpmap\LightSprites2[mpmap\LightAmount], LightSpriteTex(2))
	EntityBlend(mpmap\LightSprites2[mpmap\LightAmount], 3)
	EntityOrder(mpmap\LightSprites2[mpmap\LightAmount], -1)
	EntityColor(mpmap\LightSprites2[mpmap\LightAmount], r%, g%, b%)
	EntityFX(mpmap\LightSprites2[mpmap\LightAmount],1)
	RotateEntity(mpmap\LightSprites2[mpmap\LightAmount],0,0,Rand(360))
	SpriteViewMode(mpmap\LightSprites2[mpmap\LightAmount],1)
	mpmap\LightSpriteHidden[mpmap\LightAmount] = True
	HideEntity mpmap\LightSprites2[mpmap\LightAmount]
	mpmap\LightFlicker[mpmap\LightAmount] = Rand(1,10)
	
	mpmap\LightR[mpmap\LightAmount] = r
	mpmap\LightG[mpmap\LightAmount] = g
	mpmap\LightB[mpmap\LightAmount] = b
	
	HideEntity mpmap\Lights[mpmap\LightAmount]
	
	mpmap\LightAmount = mpmap\LightAmount + 1
	
End Function

Function UpdateLightsMPMap(cam%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local i, random#, alpha#
	
	For i=0 To mp_I\Map\LightAmount-1
		If mp_I\Map\Lights[i]<>0
			If opt\EnableRoomLights%
				ShowEntity mp_I\Map\LightSprites[i]
				
				If EntityDistanceSquared(cam%,mp_I\Map\Lights[i])<PowTwo(8.5)
					If mp_I\Map\LightHidden[i]
						ShowEntity mp_I\Map\Lights[i]
						mp_I\Map\LightHidden[i] = False
					EndIf
				Else
					If (Not mp_I\Map\LightHidden[i])
						HideEntity mp_I\Map\Lights[i]
						mp_I\Map\LightHidden[i] = True
					EndIf
				EndIf
				
				;If ml\LightCone<>0 Then ShowEntity ml\LightCone
				;If ml\LightConeSpark<>0 
				;	If ml\LightConeSparkTimer>0 And ml\LightConeSparkTimer<10
				;		ShowEntity ml\LightConeSpark
				;		ml\LightConeSparkTimer=ml\LightConeSparkTimer+FPSfactor
				;	Else
				;		HideEntity ml\LightConeSpark
				;		ml\LightConeSparkTimer=0
				;	EndIf
				;EndIf
				
				If (EntityDistanceSquared(cam%,mp_I\Map\LightSprites2[i])<PowTwo(8.5)) ;Lor ml\RoomTemplate\UseLightCones)
					If EntityVisible(cam%,mp_I\Map\LightSpritesPivot[i]) ;Lor ml\RoomTemplate\UseLightCones
						If mp_I\Map\LightSpriteHidden[i]
							ShowEntity mp_I\Map\LightSprites2[i]
							mp_I\Map\LightSpriteHidden[i] = False
						EndIf
						If mp_I\Map\LightFlicker[i]<5
							random# = Rnd(0.38,0.42)
						ElseIf mp_I\Map\LightFlicker[i]>4 And mp_I\Map\LightFlicker[i]<10
							random# = Rnd(0.35,0.45)
						Else
							random# = Rnd(0.3,0.5)
						EndIf
						ScaleSprite mp_I\Map\LightSprites2[i],random#,random#
						alpha# = Float(Inverse(Max(Min((EntityDistance(cam%,mp_I\Map\LightSpritesPivot[i])+0.5)/7.5,1.0),0.0)))
						
						If alpha# > 0.0
							EntityAlpha mp_I\Map\LightSprites2[i],Max(3*(Brightness/255)*(mp_I\Map\LightIntensity[i]/2),1)*alpha#
						Else
							;Instead of rendering the sprite invisible, just hiding it if the player is far away from it
							If (Not mp_I\Map\LightSpriteHidden[i])
								HideEntity mp_I\Map\LightSprites2[i]
								mp_I\Map\LightSpriteHidden[i]=True
							EndIf
						EndIf
					Else
						If (Not mp_I\Map\LightSpriteHidden[i])
							HideEntity mp_I\Map\LightSprites2[i]
							mp_I\Map\LightSpriteHidden[i] = True
						EndIf
					EndIf
				Else
					If (Not mp_I\Map\LightSpriteHidden[i])
						HideEntity mp_I\Map\LightSprites2[i]
						mp_I\Map\LightSpriteHidden[i] = True
						;If ml\LightCone<>0 Then HideEntity ml\LightCone
						;If ml\LightConeSpark<>0 HideEntity ml\LightConeSpark
					EndIf
				EndIf
			Else
				If (Not mp_I\Map\LightHidden[i])
					HideEntity mp_I\Map\Lights[i]
					mp_I\Map\LightHidden[i] = True
				EndIf
				If (Not mp_I\Map\LightSpriteHidden[i])
					HideEntity mp_I\Map\LightSprites2[i]
					mp_I\Map\LightSpriteHidden[i]=True
				EndIf
				;If ml\LightCone<>0 Then HideEntity ml\LightCone
				;If ml\LightConeSpark<>0 HideEntity ml\LightConeSpark
			EndIf
		EndIf
	Next
End Function

Function CreateMPGame()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local gv.GlobalVariables = First GlobalVariables
	
	Local i%,getconn%,j%
	Local tex%
	
	FreeImage mp_I\ServerIcon
	FreeImage mp_I\PasswordIcon
	
	;Will be added in a future version, currently not properly functional - ENDSHN
	;[Block]
;	;Custom spray loading
;	;For i = 0 To (mp_I\MaxPlayers-1)
;	;	If FileType("spray"+i+".cache")=1 Then
;	;		DeleteFile "spray"+i+".cache"
;	;	EndIf
;	;Next
;	Local row% = 0
;	Local currpart% = 0, shouldpart% = 0
;	Local BytesSaved% = False
;	Local file%
;	Local TempRead%
;	Local count% = 0
;	Local allFinished% = 0
;	Local byte%, bytepos%
;	If mp_I\EnableSprays And mp_I\PlayerCount>1 Then
;		Repeat
;			If Players(row)<>Null Then
;				If mp_I\PlayerID=row Then
;					If FileType(mp_I\SprayIMGPath)=1 Then
;						If (Not BytesSaved) Then
;							If Players(row)\DataBank=0 Then
;								Players(row)\DataBank = CreateBank(UDP_MAX_BYTE_AMOUNT)
;							EndIf
;							file = ReadFile(mp_I\SprayIMGPath)
;							count = 0
;							SeekFile file,bytepos
;							For i = 0 To UDP_MAX_BYTE_AMOUNT-1
;								byte = ReadByte(file)
;								PokeByte Players(row)\DataBank,i,byte
;								count = count + 1
;								If Eof(file) Then
;									Exit
;								EndIf
;							Next
;							bytepos = bytepos + count
;							BytesSaved = True
;							CloseFile file
;						EndIf
;						getconn = RecvUDPMsg(mp_I\Server)
;						While getconn
;							For i = 0 To (mp_I\MaxPlayers-1)
;								If Players(i)<>Null And i<>mp_I\PlayerID Then
;									If Players(i)\IP = getconn Then
;										If ReadLine(mp_I\Server)="wantspray" Then
;											TempRead = ReadInt(mp_I\Server)
;											Players(i)\CurrDataPackage = TempRead
;											;DebugLog "Receive response from ID "+i+"|||"+TempRead
;										EndIf
;									EndIf
;								EndIf
;							Next
;							getconn = RecvUDPMsg(mp_I\Server)
;						Wend
;						allFinished = 0
;						For i = 0 To (mp_I\MaxPlayers-1)
;							If Players(i)<>Null And i<>mp_I\PlayerID Then
;								If Players(i)\CurrDataPackage = currpart Then
;									WriteInt mp_I\Server,currpart
;									WriteInt mp_I\Server,count
;									WriteBytes(Players(row)\DataBank,mp_I\Server,0,count)
;									SendUDPMsg(mp_I\Server,Players(i)\IP,Players(i)\Port)
;									;DebugLog "Send package "+currpart
;								Else
;									allFinished = allFinished + 1
;								EndIf
;							EndIf
;						Next
;						For i = 0 To (mp_I\MaxPlayers-1)
;							If Players(i)<>Null And i<>mp_I\PlayerID Then
;								DebugLog i+"|"+Players(i)\CurrDataPackage
;							EndIf
;						Next
;						If allFinished=(mp_I\PlayerCount-1) Then
;							currpart = currpart + 1
;							BytesSaved = False
;							If count < UDP_MAX_BYTE_AMOUNT-1 Then
;								FreeBank Players(row)\DataBank
;								row = row + 1
;								currpart = 0
;								bytepos = 0
;								For i = 0 To (mp_I\MaxPlayers-1)
;									If Players(i)<>Null Then
;										Players(i)\CurrDataPackage = 0
;									EndIf
;								Next
;							EndIf
;						EndIf
;					Else
;						For i = 0 To (mp_I\MaxPlayers-1)
;							If Players(i)<>Null And i<>mp_I\PlayerID Then
;								WriteInt mp_I\Server,-1
;								SendUDPMsg(mp_I\Server,Players(i)\IP,Players(i)\Port)
;							EndIf
;						Next
;						row = row + 1
;					EndIf
;				Else
;					shouldpart = -1
;					getconn = RecvUDPMsg(mp_I\Server)
;					While getconn
;						If Players(row)\IP = getconn Then
;							If FileType("spray"+row+"_client"+mp_I\PlayerID+".cache")<>1 Then
;								file = WriteFile("spray"+row+"_client"+mp_I\PlayerID+".cache")
;							Else
;								file = OpenFile("spray"+row+"_client"+mp_I\PlayerID+".cache")
;							EndIf
;							If Players(row)\DataBank=0 Then
;								Players(row)\DataBank = CreateBank(UDP_MAX_BYTE_AMOUNT)
;							EndIf
;							DebugLog "Receive data from client ID "+row
;							shouldpart = ReadInt(mp_I\Server)
;							If shouldpart => 0 Then
;								SeekFile file,bytepos
;								count = ReadInt(mp_I\Server)
;								ReadBytes(Players(row)\DataBank,mp_I\Server,0,count)
;								If shouldpart=currpart Then
;									WriteBytes(Players(row)\DataBank,file,0,count)
;								EndIf
;								If shouldpart=currpart Then
;									currpart = currpart + 1
;									bytepos = bytepos + count
;								EndIf
;								CloseFile file
;							Else
;								FreeBank Players(row)\DataBank
;								CloseFile file
;								DeleteFile("spray"+row+"_client"+mp_I\PlayerID+".cache")
;								row = row + 1
;							EndIf
;						EndIf
;						getconn = RecvUDPMsg(mp_I\Server)
;					Wend
;					WriteLine mp_I\Server,"wantspray"
;					WriteInt mp_I\Server,currpart
;					SendUDPMsg mp_I\Server,Players(row)\IP,Players(row)\Port
;					If count < UDP_MAX_BYTE_AMOUNT-1 And shouldpart=>0 Then
;						FreeBank Players(row)\DataBank
;						row = row + 1
;						currpart = 0
;						bytepos = 0
;					EndIf
;				EndIf
;			Else
;				row = row + 1
;			EndIf
;			
;			If row > (mp_I\MaxPlayers-1) Then
;				Exit
;			EndIf
;			
;			AAText(opt\GraphicWidth / 2, opt\GraphicHeight - 50, "DOWNLOADING CUSTOM SPRAYS", True, True)
;			Flip 0
;		;JEFF
;		Forever
;	EndIf
;	
;	DrawLoading(5)
;	AAText(opt\GraphicWidth / 2, opt\GraphicHeight - 50, "LOADING CUSTOM SPRAYS", True, True)
;	Flip 1
;	
;	If mp_I\EnableSprays Then
;		Local scale# = 0.5
;		Local tex%
;		For i = 0 To (mp_I\MaxPlayers-1)
;			If Players(i)<>Null And i<>mp_I\PlayerID Then
;				If FileSize("spray"+i+"_client"+mp_I\PlayerID+".cache")>0 Then
;					tex = LoadTexture("spray"+i+"_client"+mp_I\PlayerID+".cache",1+8+16+32+256)
;					Players(i)\SpraySprite = CreateSprite()
;					EntityTexture Players(i)\SpraySprite,tex
;					DeleteSingleTextureEntryFromCache tex
;					ScaleSprite Players(i)\SpraySprite,scale,scale
;					SpriteViewMode Players(i)\SpraySprite,2
;					PositionEntity Players(i)\SpraySprite,0,-5000,0
;					EntityFX Players(i)\SpraySprite,1
;					DeleteFile "spray"+i+"_client"+mp_I\PlayerID+".cache"
;				EndIf
;			EndIf
;		Next
;		If FileSize(mp_I\SprayIMGPath)>0 Then
;			tex = LoadTexture(mp_I\SprayIMGPath,1+8+16+32+256)
;			Players(mp_I\PlayerID)\SpraySprite = CreateSprite()
;			EntityTexture Players(mp_I\PlayerID)\SpraySprite,tex
;			DeleteSingleTextureEntryFromCache tex
;			ScaleSprite Players(mp_I\PlayerID)\SpraySprite,scale,scale
;			SpriteViewMode Players(mp_I\PlayerID)\SpraySprite,2
;			PositionEntity Players(mp_I\PlayerID)\SpraySprite,0,-5000,0
;			EntityFX Players(mp_I\PlayerID)\SpraySprite,1
;		EndIf
;	EndIf
	;[End Block]
	
	If mp_I\Gamemode\ID = Gamemode_Deathmatch Then
		Players(mp_I\PlayerID)\Team = Team_Unknown
	Else
		Players(mp_I\PlayerID)\Team = Team_MTF
	EndIf
	
	DrawLoading(6)
	
	NTF_GameModeFlag = 3
	
	HideDistance# = 15.0
	
	CreateMainPlayer()
	Local mpl.MainPlayer = First MainPlayer
	
	mp_I\HealthIcon = LoadImage_Strict("GFX\hpicon.png")
	SprintIcon% = LoadImage_Strict("GFX\sprinticon.png")
	CrouchIcon% = LoadImage_Strict("GFX\sneakicon.png")
	HandIcon2% = LoadImage_Strict("GFX\handsymbol2.png")
	StaminaMeterIMG% = LoadImage_Strict("GFX\staminameter.jpg")
	
	PauseMenuIMG% = LoadImage_Strict("GFX\menu\pausemenu.jpg")
	MaskImage PauseMenuIMG, 255,255,0
	ScaleImage PauseMenuIMG,MenuScale,MenuScale
	
	mp_I\Map = New MultiplayerMap
	
	LoadMissingTexture()
	
	Brightness% = GetINIFloat(gv\OptionFile, "options", "brightness", 50)
	CameraFogNear# = GetINIFloat(gv\OptionFile, "options", "camera fog near", 0.5)
	CameraFogFar# = GetINIFloat(gv\OptionFile, "options", "camera fog far", 6.0)
	StoredCameraFogFar# = CameraFogFar
	
	AmbientLightRoomTex% = CreateTextureUsingCacheSystem(2,2,1)
	TextureBlend AmbientLightRoomTex,2
	SetBuffer(TextureBuffer(AmbientLightRoomTex))
	ClsColor 0,0,0
	Cls
	SetBuffer BackBuffer()
	AmbientLightRoomVal = 0
	
	SoundEmitter = CreatePivot()
	
	Camera = CreateCamera()
	CameraViewport Camera,0,0,opt\GraphicWidth,opt\GraphicHeight
	CameraRange(Camera, 0.01, CameraFogFar)
	CameraFogMode (Camera, 1)
	CameraFogRange (Camera, CameraFogNear, CameraFogFar)
	CameraFogColor (Camera, GetINIInt(gv\OptionFile, "options", "fog r"), GetINIInt(gv\OptionFile, "options", "fog g"), GetINIInt(gv\OptionFile, "options", "fog b"))
	AmbientLight Brightness, Brightness, Brightness
	
	;Currently is like that, will change in the future
	EntityParent Camera,mpl\CameraPivot
	
	DrawLoading(7)
	
	CreateBlurImage()
	CameraProjMode ark_blur_cam,0
	
	FogTexture = LoadTexture_Strict("GFX\fog.jpg",1,2)
	
	Fog = CreateSprite(ark_blur_cam)
	ScaleSprite(Fog, Max(opt\GraphicWidth / 1240.0, 1.0), Max(opt\GraphicHeight / 960.0 * 0.8, 0.8))
	EntityTexture(Fog, FogTexture)
	EntityBlend (Fog, 2)
	EntityOrder Fog, -1000
	MoveEntity(Fog, 0, 0, 1.0)
	
	GasMaskOverlay2 = LoadSprite("GFX\GasmaskOverlay.jpg",1,ark_blur_cam)
	ScaleSprite GasMaskOverlay2,1.0,0.57/((Float(opt\GraphicWidth)/Float(opt\GraphicHeight))/(16.0/9.0))
	EntityBlend (GasMaskOverlay2, 2)
	EntityFX(GasMaskOverlay2, 1)
	EntityOrder GasMaskOverlay2, -2000
	MoveEntity(GasMaskOverlay2, 0, 0, 1.0)
	ShowEntity(GasMaskOverlay2)
	
	DrawLoading(8)
	
	mp_I\PlayerModel_Lower[Team_MTF-1] = LoadAnimMesh_Strict("GFX\npcs\MTF_Player_Lower.b3d")
	ExtractAnimSeq mp_I\PlayerModel_Lower[Team_MTF-1], 486, 574
	ExtractAnimSeq mp_I\PlayerModel_Lower[Team_MTF-1], 1, 151
	ExtractAnimSeq mp_I\PlayerModel_Lower[Team_MTF-1], 152, 302
	ExtractAnimSeq mp_I\PlayerModel_Lower[Team_MTF-1], 303, 363
	ExtractAnimSeq mp_I\PlayerModel_Lower[Team_MTF-1], 364, 424
	ExtractAnimSeq mp_I\PlayerModel_Lower[Team_MTF-1], 425, 485
	ExtractAnimSeq mp_I\PlayerModel_Lower[Team_MTF-1], 575, 625
	ExtractAnimSeq mp_I\PlayerModel_Lower[Team_MTF-1], 626, 686
	ExtractAnimSeq mp_I\PlayerModel_Lower[Team_MTF-1], 687, 717
	ExtractAnimSeq mp_I\PlayerModel_Lower[Team_MTF-1], 718, 748
	
	mp_I\PlayerModel_Lower[Team_CI-1] = CopyEntity(mp_I\PlayerModel_Lower[Team_MTF-1])
	tex = LoadTexture_Strict("GFX\npcs\CI_newdiffuse02.jpg")
	EntityTexture mp_I\PlayerModel_Lower[Team_CI-1],tex
	DeleteSingleTextureEntryFromCache tex
	mp_I\PlayerModel_Upper[Team_MTF-1] = LoadAnimMesh_Strict("GFX\npcs\MTF_Player_Upper.b3d")
	mp_I\PlayerModel_Upper[Team_CI-1] = LoadAnimMesh_Strict("GFX\npcs\CI_Player_Upper.b3d")
	For i = 0 To (MaxPlayerTeams-1)
		;[Block]
		;Pistol
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1, 89
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 388, 538
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 90, 240
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 292, 298
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 241, 291
		;Rifle
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 299, 387
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 774, 924
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 590, 773
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 925, 931
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 539, 589
		;Shotgun
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 932, 1020
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1070, 1220
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1221, 1241
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1342, 1350
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1291, 1341
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1021, 1069
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1242, 1290
		;SMG
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1351, 1439
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1440, 1590
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1591, 1811
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1812, 1816
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1817, 1867
		;Melee
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1868, 1956
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 1957, 2107
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 2108, 2148
		;MP5K
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 2149, 2237
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 2238, 2388
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 2389, 2529
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 2530, 2535
		ExtractAnimSeq mp_I\PlayerModel_Upper[i], 2536, 2586
		;[End Block]
		HideEntity mp_I\PlayerModel_Lower[i]
		HideEntity mp_I\PlayerModel_Upper[i]
	Next
	
	If mp_I\Gamemode\ID = Gamemode_Waves Then
		;Zombie models
		mp_I\ZombieModel[0] = LoadAnimMesh_Strict("GFX\npcs\zombies\zombie1.b3d")
		mp_I\ZombieModel[1] = LoadAnimMesh_Strict("GFX\npcs\zombies\zombie2.b3d")
		mp_I\ZombieModel[2] = LoadAnimMesh_Strict("GFX\npcs\zombies\Zombieclassd.b3d")
		mp_I\ZombieModel[3] = LoadAnimMesh_Strict("GFX\npcs\zombies\zombieclerk.b3d")
		mp_I\ZombieModel[4] = LoadAnimMesh_Strict("GFX\npcs\zombies\zombiesurgeon.b3d")
		mp_I\ZombieModel[5] = CopyEntity(mp_I\ZombieModel[2])
		tex = LoadTexture_Strict("GFX\npcs\zombies\dclass_zombie_2.jpg")
		EntityTexture mp_I\ZombieModel[5],tex
		DeleteSingleTextureEntryFromCache tex
		mp_I\ZombieModel[6] = CopyEntity(mp_I\ZombieModel[2])
		tex = LoadTexture_Strict("GFX\npcs\zombies\scientist_zombie_1.jpg")
		EntityTexture mp_I\ZombieModel[6],tex
		DeleteSingleTextureEntryFromCache tex
		mp_I\ZombieModel[7] = CopyEntity(mp_I\ZombieModel[2])
		tex = LoadTexture_Strict("GFX\npcs\zombies\janitor_zombie.jpg")
		EntityTexture mp_I\ZombieModel[7],tex
		DeleteSingleTextureEntryFromCache tex
		For i = 0 To MaxZombieTypes-1
			HideEntity mp_I\ZombieModel[i]
		Next
		;Guard zombie models
		mp_I\GuardZombieModel[0] = LoadAnimMesh_Strict("GFX\npcs\zombies\guardzombie.b3d")
		mp_I\GuardZombieModel[1] = LoadAnimMesh_Strict("GFX\npcs\zombies\MTFzombie.b3d")
		For i = 0 To MaxGuardZombieTypes-1
			HideEntity mp_I\GuardZombieModel[i]
		Next
		;SCP-939 model
		mp_I\SCP939Model = LoadAnimMesh_Strict("GFX\npcs\scp-939_mp.b3d")
		HideEntity mp_I\SCP939Model
		;SCP-1048-a model
		mp_I\SCP1048aModel = LoadAnimMesh_Strict("GFX\npcs\scp-1048a_mp.b3d")
		HideEntity mp_I\SCP1048aModel
		;Tentacle model
		mp_I\TentacleModel = LoadAnimMesh_Strict("GFX\npcs\035tentacle.b3d")
		HideEntity mp_I\TentacleModel
		;Boss model, depending on the map
		Select mp_I\MapInList\BossNPC
			Case "SCP-035"
				mp_I\BossModel = LoadAnimMesh_Strict("GFX\npcs\035.b3d")
			Case "SCP-457"
				mp_I\BossModel = LoadAnimMesh_Strict("GFX\npcs\106_2.b3d")
			Default
				RuntimeError "Error: Boss NPC " + mp_I\MapInList\BossNPC + " is not a boss or doesn't exist."
		End Select
		HideEntity mp_I\BossModel
	EndIf 
	
	DrawLoading(9)
	
	LightSpriteTex(0) = LoadTexture_Strict("GFX\light1.jpg",1,2)
	LightSpriteTex(1) = LoadTexture_Strict("GFX\light2.jpg",1,2)
	LightSpriteTex(2) = LoadTexture_Strict("GFX\lightsprite.jpg",1,2)
	
	LastItemID% = 0
	InitMPItemTemplates()
	
	DrawLoading(10)
	
	LoadMaterials("Data\materials.ini")
	
	For i = 0 To 7
		NTF_PainSFX(i)=LoadSound_Strict("SFX\Player\pain"+(i+1)+".ogg")
	Next
	For i = 0 To 1
		NTF_PainWeakSFX(i)=LoadSound_Strict("SFX\Player\painweak"+(i+1)+".ogg")
	Next
	
	DecalTextures(15) = LoadTexture_Strict("GFX\decal035.png",1+2,2)
	For i = 13 To 14
		DecalTextures(i) = LoadTexture_Strict("GFX\bullethole"+(i-12)+".jpg",1+2,2)
	Next
	
	ParticleTextures(0) = LoadTexture_Strict("GFX\smoke.png",1+2,2)
	ParticleTextures(1) = LoadTexture_Strict("GFX\flash.jpg",1+2,2)
	ParticleTextures(2) = LoadTexture_Strict("GFX\dust.jpg",1+2,2)
	;ParticleTextures(3) = LoadTexture_Strict("GFX\npcs\hg.pt",1+2,2)
	;ParticleTextures(4) = LoadTexture_Strict("GFX\map\sun.jpg",1+2,2)
	ParticleTextures(5) = LoadTexture_Strict("GFX\bloodsprite.png",1+2,2)
	ParticleTextures(6) = LoadTexture_Strict("GFX\smoke2.png",1+2,2)
	ParticleTextures(7) = LoadTexture_Strict("GFX\spark.jpg",1+2,2)
	ParticleTextures(8) = LoadTexture_Strict("GFX\particle.png",1+2,2)
	;ParticleTextures(9) = LoadAnimTexture("NineTailedFoxMod\GFX\fog_textures.png",1+2,256,256,0,4) ;Maybe this can be useful at some point?
	ParticleTextures(12) = LoadTexture_Strict("GFX\fire_particle.png",1+2,2)
	
	DrawLoading(12)
	
	InitGuns()
	
	mp_I\SpectatePlayer = -1
	mp_I\DeathChunk = -1
	
	mp_I\MuzzleFlash = CreateSprite()
	EntityTexture mp_I\MuzzleFlash,ParticleTextures(1)
	HideEntity mp_I\MuzzleFlash
	
	DrawLoading(15)
	
	LoadAllSounds()
	
End Function

Function InitMPGame()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local fo.Fonts = First Fonts
	Local g_I.GunInstance = First GunInstance
	Local n2.NPCs
	Local i%
	
	FreeEntity Players(mp_I\PlayerID)\GunPivot
	Players(mp_I\PlayerID)\GunPivot = g_I\GunPivot
	
	For n2.NPCs = Each NPCs
		HideNPCHitBoxes(n2)
	Next
	
	Local mmt.MultiplayerMapTemplate
	For mmt = Each MultiplayerMapTemplate
		FreeEntity mmt\obj
		Delete mmt
	Next
	
	DrawLoading(79)
	
	MoveMouse viewport_center_x,viewport_center_y
	
	AASetFont fo\Font[Font_Default]
	
	HidePointer()
	
	FlushKeys()
	
	DeleteTextureEntriesFromCache(0)
	
	DrawLoading(90)
	
	NoClipSpeed = 2.0
	
	InitConsole(3)
	
	FlushMouse()
	
	Players(mp_I\PlayerID)\DropSpeed = 0
	
	PrevTime = MilliSecs()
	
End Function

Function CreateMPWaypoint.WayPoints(x#,y#,z#)
	
	Local w.WayPoints = New WayPoints
	
	If 1 Then
		w\obj = CreatePivot()
		PositionEntity w\obj, x,y,z	
	Else
		w\obj = CreateSprite()
		PositionEntity(w\obj, x, y, z)
		ScaleSprite(w\obj, 0.15 , 0.15)
		EntityTexture(w\obj, LightSpriteTex(0))
		EntityBlend (w\obj, 3)	
	EndIf
	
	Return w
End Function

Function InitMPWayPoints(loadingstart=45,drawloadingscreen=True)
	Local d.Doors, w.WayPoints, w2.WayPoints, r.Rooms, ClosestRoom.Rooms
	
	Local x#, y#, z#
	
	temper = MilliSecs()
	
	Local dist#, dist2#
	
;	For d.Doors = Each Doors
;		If d\obj <> 0 Then HideEntity d\obj
;		If d\obj2 <> 0 Then HideEntity d\obj2	
;		If d\frameobj <> 0 Then HideEntity d\frameobj
;		
;		If d\room = Null Then 
;			ClosestRoom.Rooms = Null
;			dist# = 30
;			For r.Rooms = Each Rooms
;				x# = Abs(EntityX(r\obj,True)-EntityX(d\frameobj,True))
;				If x < 20.0 Then
;					z# = Abs(EntityZ(r\obj,True)-EntityZ(d\frameobj,True))
;					If z < 20.0 Then
;						dist2 = x*x+z*z
;						If dist2 < dist Then
;							ClosestRoom = r
;							dist = dist2
;						EndIf
;					EndIf
;				EndIf
;			Next
;		Else
;			ClosestRoom = d\room
;		EndIf
;		
;		If (Not d\DisableWaypoint) Then CreateWaypoint(EntityX(d\frameobj, True), EntityY(d\frameobj, True)+0.18, EntityZ(d\frameobj, True), d, ClosestRoom)
;	Next
	
	amount# = 0
	For w.WayPoints = Each WayPoints
		EntityPickMode w\obj, 1, True
		EntityRadius w\obj, 0.2
		amount=amount+1
	Next
	
	number = 0
	iter = 0
	For w.WayPoints = Each WayPoints
		
		number = number + 1
		iter = iter + 1
		If iter = 20 Then
			If drawloadingscreen Then DrawLoading(loadingstart+Floor((35.0/amount)*number))
			iter = 0
		EndIf
		
		w2.WayPoints = After(w)
		
		Local canCreateWayPoint% = False
		
		While (w2<>Null)
			
			If True ;If (w\room=w2\room Lor w\door<>Null Lor w2\door<>Null)
				
				dist# = EntityDistance(w\obj, w2\obj) ;TODO waypoint dist
				
;				If w\room\MaxWayPointY# = 0.0 Lor w2\room\MaxWayPointY# = 0.0
;					canCreateWayPoint = True
;				Else
;					If Abs(EntityY(w\obj)-EntityY(w2\obj))<=w\room\MaxWayPointY
;						canCreateWayPoint = True
;					EndIf
;				EndIf
				canCreateWayPoint = True
				
				If dist < 7.0 Then
					If canCreateWayPoint
						If EntityVisible(w\obj, w2\obj) Then 
							For i = 0 To 4
								If w\connected[i] = Null Then
									w\connected[i] = w2.WayPoints 
									w\dist[i] = dist
									Exit
								EndIf
							Next
							
							For n = 0 To 4
								If w2\connected[n] = Null Then 
									w2\connected[n] = w.WayPoints 
									w2\dist[n] = dist
									Exit
								EndIf					
							Next
						EndIf
					EndIf	
				EndIf
			EndIf
			w2 = After(w2)
		Wend
		
	Next
	
;	For d.Doors = Each Doors
;		If d\obj <> 0 Then ShowEntity d\obj
;		If d\obj2 <> 0 Then ShowEntity d\obj2	
;		If d\frameobj <> 0 Then ShowEntity d\frameobj		
;	Next
	
	For w.WayPoints = Each WayPoints
		EntityPickMode w\obj, 0, 0
		EntityRadius w\obj, 0
		
		For i = 0 To 4
			If w\connected[i]<>Null Then 
				tline = CreateLine(EntityX(w\obj,True),EntityY(w\obj,True),EntityZ(w\obj,True),EntityX(w\connected[i]\obj,True),EntityY(w\connected[i]\obj,True),EntityZ(w\connected[i]\obj,True))
				EntityColor(tline, 255,0,0)
				EntityParent tline, w\obj
			EndIf
		Next
	Next
	
	DebugLog "InitWaypoints() - "+(MilliSecs()-temper)
End Function

Function MPMainLoop()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local ft.FixedTimesteps = First FixedTimesteps
	Local co.Controller = First Controller
	Local fo.Fonts = First Fonts
	Local g_I.GunInstance = First GunInstance
	Local i%
	Local prevGun[MaxPlayers-1]
	
	While (ft\accumulator>0.0)
		ft\accumulator = ft\accumulator-GetTickDuration()
		If (ft\accumulator<=0.0) Then CaptureWorld()
		
		For i=0 To (mp_I\MaxPlayers-1)
			If Players(i)<>Null Then
				prevGun[i] = Players(i)\WeaponInSlot[Players(i)\SelectedSlot]
			EndIf
		Next
		
		RecvDataServer()
		
		If Input_ResetTime>0
			Input_ResetTime = Max(Input_ResetTime-FPSfactor,0.0)
		Else
			DoubleClick = False
			If (Not co\Enabled)
				MouseHit1 = MouseHit(1)
				If MouseHit1
					If MilliSecs() - LastMouseHit1 < 800 Then DoubleClick = True
					LastMouseHit1 = MilliSecs()
				EndIf
				Local prevmousedown1 = MouseDown1
				MouseDown1 = MouseDown(1)
				If prevmousedown1 = True And MouseDown1=False Then MouseUp1 = True Else MouseUp1 = False
				
				MouseHit2 = MouseHit(2)
				
				MouseHit3 = MouseHit(3)
				
				keyhituse = KeyHit(KEY_USE)
				keydownuse = KeyDown(KEY_USE)
			Else
				;[CONTROLLER]
				MouseHit1 = JoyHit(CK_LMouse)
				If MouseHit1 Then
					If MilliSecs() - LastMouseHit1 < 800 Then DoubleClick = True
					LastMouseHit1 = MilliSecs()
				EndIf
				prevmousedown1 = MouseDown1
				MouseDown1 = JoyDown(CK_LMouse)
				If prevmousedown1 = True And MouseDown1=False Then MouseUp1 = True Else MouseUp1 = False
				MouseHit2 = JoyHit(CK_RMouse)
				MouseHit3 = JoyHit(CK_MMouse)
				keyhituse = JoyHit(CK_Use)
				keydownuse = JoyDown(CK_Use)
			EndIf
		EndIf
		
		If (Not keydownuse) And (Not keyhituse) Then GrabbedEntity = 0
		
		UpdateMusic()
		If opt\EnableSFXRelease Then AutoReleaseSounds()
		
		DrawHandIcon = False
		
		RestoreSanity = True
		ShouldEntitiesFall = True
		
		Select mp_I\Gamemode\ID
			Case Gamemode_Waves
				If (mp_I\Gamemode\Phase Mod 2)=1 Then
					ShouldPlay = 37
				Else
					ShouldPlay = 36
				EndIf
			Case Gamemode_Deathmatch,Gamemode_Gungame
				ShouldPlay = 36
		End Select
		
		co\PressedButton = JoyHit(CKM_Press)
		co\PressedNext = JoyDown(CKM_Next)
		co\PressedPrev = JoyDown(CKM_Prev)
		If co\PressedNext And co\PressedPrev
			co\PressedNext = False
			co\PressedPrev = False
		EndIf
		
		LightVolume = CurveValue(TempLightVolume, LightVolume, 50.0)
		;CameraFogRange(Camera, CameraFogNear*LightVolume,CameraFogFar*LightVolume)
		CameraFogColor(Camera, 0,0,0)
		CameraFogMode Camera,1
		;CameraRange(Camera, 0.01, Min(CameraFogFar*LightVolume*1.5,28))
		
		AmbientLight Brightness, Brightness, Brightness	
		PlayerSoundVolume = CurveValue(0.0, PlayerSoundVolume, 5.0)
		
		UpdateGUIMP()
		
		MouseLookServer()
		If Players(mp_I\PlayerID)\Team > Team_Spectator Then
			MovePlayerServer()
			ShowEntity GasMaskOverlay2
		Else
			UpdateSpectatorPosition(mp_I\PlayerID)
			HideEntity g_I\GunPivot
			HideEntity GasMaskOverlay2
		EndIf
		UpdatePlayerPositionsServer()
		UpdateItemSpawners()
		UpdateEnemySpawners()
		UpdateMPItemsGravity()
		UpdateMPItems()
		UpdateNPCsServer()
		UpdateLightsMPMap(Camera)
		UpdateFluLights()
		If Players(mp_I\PlayerID)\Team > Team_Spectator Then
			UpdateGunsClient()
		EndIf
		UpdatePlayerGunsServer()
		PlayGunSoundsMP()
		AnimateGunsServer()
		If Players(mp_I\PlayerID)\CurrHP > 0 Then
			UpdateIronSight()
		EndIf
		UpdateChunksMP()
		;PlaceSpray()
		UpdateDecals()
		UpdateParticles()
		AnimatePlayerModelsAndSpectate()
		UpdateNightVision()
		UpdateWorld()
		GetPlayerPositions()
		ManipulateNPCBones()
		ManipulatePlayerModelBones()
		UpdateGamemodeMP()
		
		BlurVolume = Min(CurveValue(0.0, BlurVolume, 20.0),0.95)
		If BlurTimer > 0.0 Then
			BlurVolume = Max(Min(0.95, BlurTimer / 1000.0), BlurVolume)
			BlurTimer = Max(BlurTimer - FPSfactor, 0.0)
		EndIf
		
		If KeyHit(KEY_CONSOLE) Then
			If opt\CanOpenConsole
				If ConsoleOpen Then
					UsedConsole = True
					ResumeSounds()
					MouseXSpeed() : MouseYSpeed() : MouseZSpeed() : mouse_x_speed_1#=0.0 : mouse_y_speed_1#=0.0
				Else
					PauseSounds()
				EndIf
				ConsoleOpen = (Not ConsoleOpen)
				FlushKeys()
			EndIf
		EndIf
		UpdateMPMenu()
		
		If MainMenuOpen Then Return
		
		If MsgTimer>0
			MsgTimer=MsgTimer-FPSfactor2
		EndIf
		
		For i = 0 To (mp_I\MaxPlayers-1)
			If Players(i)<>Null Then
				If Players(i)\WeaponInSlot[Players(i)\SelectedSlot] <> prevGun[i] Then
					SwitchPlayerGun(i)
				EndIf
			EndIf
		Next
		
		SyncServer()
		UpdateAchievementMsg()
	Wend
	CameraProjMode ark_blur_cam,0
	CameraProjMode Camera,1
	If MainPl\NightVisionEnabled=3
		AmbientLight 255,255,255
	ElseIf PlayerRoom<>Null
		AmbientLight Brightness, Brightness, Brightness
	EndIf
	RenderWorld(Max(0.0,1.0+(ft\accumulator/ft\tickDuration)))
	CurrTrisAmount = TrisRendered()
	;render sprites
	CameraProjMode ark_blur_cam,2
	CameraProjMode Camera,0
	RenderWorld()
	CameraProjMode ark_blur_cam,0
	
	UpdateBlur(BlurVolume)
	
	DrawGUIMP()
	
	DrawMPMenu()
	
	UpdateConsole(3)
	
	If MsgTimer > 0 Then
		AASetFont fo\Font[Font_Default]
		Color 0,0,0
		AAText((opt\GraphicWidth / 2)+1, (opt\GraphicHeight / 2) + 201, Msg, True, False, Min(MsgTimer / 2, 255)/255.0)
		Color 255,255,255
		If Left(Msg,14)="Blitz3D Error!" Then
			Color 255,0,0
		EndIf
		AAText((opt\GraphicWidth / 2), (opt\GraphicHeight / 2) + 200, Msg, True, False, Min(MsgTimer / 2, 255)/255.0)
	EndIf
	
	Color 255, 255, 255
	If opt\ShowFPS Then AASetFont fo\ConsoleFont : AAText 20, 20, "FPS: " + ft\fps : AASetFont fo\Font[Font_Default]
	
	RenderAchievementMsg()
	
End Function

Function MPMainLoopClient()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local ft.FixedTimesteps = First FixedTimesteps
	Local co.Controller = First Controller
	Local fo.Fonts = First Fonts
	Local g_I.GunInstance = First GunInstance
	Local IsOnTeam%
	Local i%
	Local prevGun[MaxPlayers-1]
	
	While (ft\accumulator>0.0)
		ft\accumulator = ft\accumulator-GetTickDuration()
		If (ft\accumulator<=0.0) Then CaptureWorld()
		
		For i=0 To (mp_I\MaxPlayers-1)
			If Players(i)<>Null Then
				prevGun[i] = Players(i)\WeaponInSlot[Players(i)\SelectedSlot]
			EndIf
		Next
		
		RecvDataClient()
		If MainMenuOpen Then Return
		
		If Input_ResetTime>0
			Input_ResetTime = Max(Input_ResetTime-FPSfactor,0.0)
		Else
			DoubleClick = False
			If (Not co\Enabled)
				MouseHit1 = MouseHit(1)
				If MouseHit1
					If MilliSecs() - LastMouseHit1 < 800 Then DoubleClick = True
					LastMouseHit1 = MilliSecs()
				EndIf
				Local prevmousedown1 = MouseDown1
				MouseDown1 = MouseDown(1)
				If prevmousedown1 = True And MouseDown1=False Then MouseUp1 = True Else MouseUp1 = False
				
				MouseHit2 = MouseHit(2)
				
				MouseHit3 = MouseHit(3)
				
				keyhituse = KeyHit(KEY_USE)
				keydownuse = KeyDown(KEY_USE)
			Else
				;[CONTROLLER]
				MouseHit1 = JoyHit(CK_LMouse)
				If MouseHit1 Then
					If MilliSecs() - LastMouseHit1 < 800 Then DoubleClick = True
					LastMouseHit1 = MilliSecs()
				EndIf
				prevmousedown1 = MouseDown1
				MouseDown1 = JoyDown(CK_LMouse)
				If prevmousedown1 = True And MouseDown1=False Then MouseUp1 = True Else MouseUp1 = False
				MouseHit2 = JoyHit(CK_RMouse)
				MouseHit3 = JoyHit(CK_MMouse)
				keyhituse = JoyHit(CK_Use)
				keydownuse = JoyDown(CK_Use)
			EndIf
		EndIf
		
		If (Not keydownuse) And (Not keyhituse) Then GrabbedEntity = 0
		
		UpdateMusic()
		If opt\EnableSFXRelease Then AutoReleaseSounds()
		
		DrawHandIcon = False
		
		RestoreSanity = True
		ShouldEntitiesFall = True
		
		Select mp_I\Gamemode\ID
			Case Gamemode_Waves
				If (mp_I\Gamemode\Phase Mod 2)=1 Then
					ShouldPlay = 37
				Else
					ShouldPlay = 36
				EndIf
			Case Gamemode_Deathmatch,Gamemode_Gungame
				ShouldPlay = 36
		End Select
		
		co\PressedButton = JoyHit(CKM_Press)
		co\PressedNext = JoyDown(CKM_Next)
		co\PressedPrev = JoyDown(CKM_Prev)
		If co\PressedNext And co\PressedPrev
			co\PressedNext = False
			co\PressedPrev = False
		EndIf
		
		LightVolume = CurveValue(TempLightVolume, LightVolume, 50.0)
		;CameraFogRange(Camera, CameraFogNear*LightVolume,CameraFogFar*LightVolume)
		CameraFogColor(Camera, 0,0,0)
		CameraFogMode Camera,1
		;CameraRange(Camera, 0.01, Min(CameraFogFar*LightVolume*1.5,28))
		
		AmbientLight Brightness, Brightness, Brightness	
		PlayerSoundVolume = CurveValue(0.0, PlayerSoundVolume, 5.0)
		
		UpdateGUIMP()
		
		MouseLookServer()
		UpdateFluLights()
		If Players(mp_I\PlayerID)\Team > Team_Spectator Then
			MovePlayerServer()
			UpdatePlayerPosition(mp_I\PlayerID)
			ShowEntity GasMaskOverlay2
		Else
			UpdateSpectatorPosition(mp_I\PlayerID)
			HideEntity g_I\GunPivot
			HideEntity GasMaskOverlay2
		EndIf
		UpdateMPItems()
		;UpdateNPCsClient()
		UpdateNPCsServer()
		UpdateLightsMPMap(Camera)
		If Players(mp_I\PlayerID)\Team > Team_Spectator Then
			UpdateGunsClient()
			UpdatePlayerGun(mp_I\PlayerID)
		EndIf
		PlayGunSoundsMP()
		AnimateGunsServer()
		If Players(mp_I\PlayerID)\CurrHP > 0 Then
			UpdateIronSight()
		EndIf
		;PlaceSpray()
		UpdateChunksMP()
		UpdateDecals()
		UpdateParticles()
		AnimatePlayerModelsAndSpectate()
		UpdateNightVision()
		UpdateWorld()
		GetPlayerPositions()
		ManipulateNPCBones()
		ManipulatePlayerModelBones()
		
		BlurVolume = Min(CurveValue(0.0, BlurVolume, 20.0),0.95)
		If BlurTimer > 0.0 Then
			BlurVolume = Max(Min(0.95, BlurTimer / 1000.0), BlurVolume)
			BlurTimer = Max(BlurTimer - FPSfactor, 0.0)
		EndIf
		
		UpdateMPMenu()
		
		If MainMenuOpen Then Return
		
		If MsgTimer>0
			MsgTimer=MsgTimer-FPSfactor2
		EndIf
		
		For i = 0 To (mp_I\MaxPlayers-1)
			If Players(i)<>Null Then
				If Players(i)\WeaponInSlot[Players(i)\SelectedSlot] <> prevGun[i] Then
					SwitchPlayerGun(i)
				EndIf
			EndIf
		Next
		
		SyncClient()
		UpdateAchievementMsg()
		If MainMenuOpen Then Return
	Wend
	CameraProjMode ark_blur_cam,0
	CameraProjMode Camera,1
	If MainPl\NightVisionEnabled=3
		AmbientLight 255,255,255
	ElseIf PlayerRoom<>Null
		AmbientLight Brightness, Brightness, Brightness
	EndIf
	RenderWorld(Max(0.0,1.0+(ft\accumulator/ft\tickDuration)))
	CurrTrisAmount = TrisRendered()
	;render sprites
	CameraProjMode ark_blur_cam,2
	CameraProjMode Camera,0
	RenderWorld()
	CameraProjMode ark_blur_cam,0
	
	UpdateBlur(BlurVolume)
	
	DrawGUIMP()
	
	DrawMPMenu()
	
	If MsgTimer > 0 Then
		AASetFont fo\Font[Font_Default]
		Color 0,0,0
		AAText((opt\GraphicWidth / 2)+1, (opt\GraphicHeight / 2) + 201, Msg, True, False, Min(MsgTimer / 2, 255)/255.0)
		Color 255,255,255
		If Left(Msg,14)="Blitz3D Error!" Then
			Color 255,0,0
		EndIf
		AAText((opt\GraphicWidth / 2), (opt\GraphicHeight / 2) + 200, Msg, True, False, Min(MsgTimer / 2, 255)/255.0)
	EndIf
	
	Color 255, 255, 255
	If opt\ShowFPS Then AASetFont fo\ConsoleFont : AAText 20, 20, "FPS: " + ft\fps : AASetFont fo\Font[Font_Default]
	
	RenderAchievementMsg()
	
End Function

Function MovePlayerServer()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local co.Controller = First Controller
	CatchErrors("Uncaught (MovePlayerServer)")
	Local Sprint# = 1.0, Speed# = 0.018, i%, angle#
	Local temp#
	
	If (Not MenuOpen) And (Not ConsoleOpen) And (Not InTeamSelection()) And Players(mp_I\PlayerID)\CurrHP>0 Then ;And (Not Players(mp_I\PlayerID)\Disable) Then
		If KeyDown(KEY_SPRINT) Then
			If Players(mp_I\PlayerID)\CurrStamina < 5 Then
				If ChannelPlaying(BreathCHN)=False Then BreathCHN = PlaySound_Strict(BreathSFX((0), 0))
			ElseIf Players(mp_I\PlayerID)\CurrStamina < 50
				If BreathCHN=0 Then
					BreathCHN = PlaySound_Strict(BreathSFX((0), Rand(1,3)))
					ChannelVolume BreathCHN, Min((70.0-Players(mp_I\PlayerID)\CurrStamina)/70.0,1.0)*opt\SFXVolume
				Else
					If ChannelPlaying(BreathCHN)=False Then
						BreathCHN = PlaySound_Strict(BreathSFX((0), Rand(1,3)))
						ChannelVolume BreathCHN, Min((70.0-Players(mp_I\PlayerID)\CurrStamina)/70.0,1.0)*opt\SFXVolume			
					EndIf
				EndIf
			EndIf
			
			If Players(mp_I\PlayerID)\CurrStamina > 0 And (Not Players(mp_I\PlayerID)\Crouch) And (Players(mp_I\PlayerID)\IronSight = False) Then
				Sprint = 2.5
			EndIf
		EndIf
		
		Sprint = (Sprint / (1.0+Players(mp_I\PlayerID)\Crouch))
		
		;[CONTROLLER]
		If co\Enabled
			Local case1% = 0
			Local case2% = ((GetLeftAnalogStickPitch()<>0 Lor GetLeftAnalogStickYaw()<>0) And Playable)
		Else
			case1% = ((KeyDown(KEY_DOWN) Lor KeyDown(KEY_UP)) Lor (KeyDown(KEY_RIGHT) Lor KeyDown(KEY_LEFT)) And Playable)
			case2% = 0
		EndIf
		If case1% Lor case2% Then
			
			;[CONTROLLER]
			Local SprintKeyAssigned = False
			If (Not co\Enabled) Then
				If KeyDown(KEY_SPRINT) Then SprintKeyAssigned = True
			Else
				If Players(mp_I\PlayerID)\IsPlayerSprinting Then
					If JoyHit(CK_Sprint)
						SprintKeyAssigned = 0
					Else
						SprintKeyAssigned = 1
					EndIf
				Else
					If JoyHit(CK_Sprint)
						SprintKeyAssigned = 1
					Else
						SprintKeyAssigned = 0
					EndIf
				EndIf
			EndIf
			
			If SprintKeyAssigned Then
				Players(mp_I\PlayerID)\PressSprint = True
			Else
				Players(mp_I\PlayerID)\PressSprint = False
			EndIf
			
			temp# = (Shake Mod 360)
			Local tempchn%
			If (Not UnableToMove%) Then Shake# = (Shake + FPSfactor * Min(Sprint, 1.7) * 10) Mod 720
			If temp < 180 And (Shake Mod 360) >= 180 And KillTimer>=0 Then
				If CurrStepSFX=0 Then
					temp = GetStepSound(Players(mp_I\PlayerID)\Collider)
					If Sprint = 1.0 Then
						PlayerSoundVolume = Max(4.0,PlayerSoundVolume)
						tempchn% = PlaySound_Strict(MainPl\StepSoundWalk[Rand(0,MaxStepSounds-1)+(temp*MaxStepSounds)])
						ChannelVolume tempchn, (1.0-(Players(mp_I\PlayerID)\Crouch*0.6))*opt\SFXVolume#
					Else
						PlayerSoundVolume = Max(2.5-(Players(mp_I\PlayerID)\Crouch*0.6),PlayerSoundVolume)
						tempchn% = PlaySound_Strict(MainPl\StepSoundRun[Rand(0,MaxStepSounds-1)+(temp*MaxStepSounds)])
						ChannelVolume tempchn, (1.0-(Players(mp_I\PlayerID)\Crouch*0.6))*opt\SFXVolume#
					EndIf
				EndIf
			EndIf
		EndIf
		
		;[CONTROLLER]
		If Players(mp_I\PlayerID)\CurrHP > 0 Then
			If (Not co\Enabled)
				If KeyHit(KEY_CROUCH) And Playable Then Players(mp_I\PlayerID)\Crouch = (Not Players(mp_I\PlayerID)\Crouch)
			Else
				If JoyHit(CK_Crouch) And Playable Then Players(mp_I\PlayerID)\Crouch = (Not Players(mp_I\PlayerID)\Crouch)
			EndIf
		EndIf
		
		temp = False
		If (Not co\Enabled)
			If KeyDown(KEY_DOWN) And Playable Then
				temp = True
				angle = 180
				If KeyDown(KEY_LEFT) Then angle = 135 
				If KeyDown(KEY_RIGHT) Then angle = -135
			ElseIf (KeyDown(KEY_UP) And Playable) Then
				temp = True
				angle = 0
				If KeyDown(KEY_LEFT) Then angle = 45
				If KeyDown(KEY_RIGHT) Then angle = -45
			Else If Playable Then
				If KeyDown(KEY_LEFT) Then angle = 90 : temp = True
				If KeyDown(KEY_RIGHT) Then angle = -90 : temp = True 
			EndIf
		Else
			;[CONTROLLER]
			If GetLeftAnalogStickPitch()<0.0 And Playable
				temp = True
				angle = 180
				If GetLeftAnalogStickYaw(True)<>0.0
					angle = GetLeftAnalogStickYaw(True,True)*(180.0-(45.0*Abs(GetLeftAnalogStickYaw())))
				EndIf
			ElseIf GetLeftAnalogStickPitch()>0.0 And Playable
				temp = True
				angle = 0
				If GetLeftAnalogStickYaw(True)<>0.0
					angle = GetLeftAnalogStickYaw(True,True)*(45.0*Abs(GetLeftAnalogStickYaw()))
				EndIf
			ElseIf Playable
				If GetLeftAnalogStickYaw(True)<>0.0
					angle = GetLeftAnalogStickYaw(True,True)*90.0
					temp = True
				EndIf
			EndIf
		EndIf
		
		If temp Then
			Players(mp_I\PlayerID)\walking = True
			Players(mp_I\PlayerID)\walkangle = angle
		Else
			Players(mp_I\PlayerID)\walking = False
		EndIf
	Else
		Players(mp_I\PlayerID)\walking = False
		Players(mp_I\PlayerID)\PressSprint = False
		Players(mp_I\PlayerID)\walkangle = 0
	EndIf
	
	CatchErrors("MovePlayerServer")
End Function

Function UpdatePlayerPositionsServer()
	CatchErrors("UpdatePlayerPositionsServer")
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local i%
	
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			If Players(i)\Team > Team_Spectator Then
				If Players(i)\CurrSpeed > 0 Then
					Players(i)\CurrStamina = Min(Players(i)\CurrStamina + 0.15 * FPSfactor/1.25, 100.0)
				Else
					Players(i)\CurrStamina = Min(Players(i)\CurrStamina + 0.15 * FPSfactor*1.25, 100.0)
				EndIf
				
				UpdatePlayerPosition(i)
				
				If i<>mp_I\PlayerID Then
					Local campitch# = Players(i)\Pitch+180
					Local camyaw# = Players(i)\Yaw
					Local gpivotpitch# = EntityPitch(Players(i)\GunPivot)+180
					Local gpivotyaw# = EntityYaw(Players(i)\GunPivot)
					Local pitch# = Clamp(CurveAngle(campitch, gpivotpitch, 10.0), campitch-2.5, campitch+2.5)
					Local yaw# = CurveAngle(camyaw, gpivotyaw, 10.0)
					
					yaw = ClampAngle(yaw, camyaw, 2.5)
					
					RotateEntity Players(i)\GunPivot,pitch-180,yaw,0
				EndIf
			EndIf
		EndIf
	Next
	
	CatchErrors("Uncaught (UpdatePlayerPositionsServer)")
End Function

Function UpdatePlayerPosition(playerID%)
	CatchErrors("UpdatePlayerPosition (" + playerID + ")")
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local Sprint# = 1.0, Speed# = 0.018, angle#, j%
	Local temp#
	
	If Abs(Players(playerID)\CrouchState-Players(playerID)\Crouch)<0.001 Then 
		Players(playerID)\CrouchState = Players(playerID)\Crouch
	Else
		Players(playerID)\CrouchState = CurveValue(Players(playerID)\Crouch, Players(playerID)\CrouchState, 10.0)
	EndIf
	
	If Players(playerID)\walking Then
		
		Players(playerID)\IsPlayerSprinting% = False
		
		If Players(playerID)\Crouch = 0 And (Players(playerID)\PressSprint) And Players(playerID)\CurrStamina > 0.0 And Players(playerID)\IronSight = 0 Then
			Sprint = 2.5
			Players(playerID)\IsPlayerSprinting% = True
			Players(playerID)\CurrStamina = Players(playerID)\CurrStamina - FPSfactor * 0.25
			If Players(playerID)\CurrStamina <= 0 Then Players(playerID)\CurrStamina = -20.0
		EndIf
	Else
		Players(playerID)\IsPlayerSprinting% = False
	EndIf
	
	Local temp2# = (Speed * Sprint) / (1.0+Players(playerID)\CrouchState)
	
	temp = False
	If Players(playerID)\walking Then
		temp = True
		angle = Players(playerID)\walkangle
	EndIf
	
	angle = WrapAngle(Players(playerID)\Yaw+angle+90.0)
	
	If temp Then 
		Players(playerID)\CurrSpeed = CurveValue(temp2, Players(playerID)\CurrSpeed, 20.0)
	Else
		Players(playerID)\CurrSpeed = Max(CurveValue(0.0, Players(playerID)\CurrSpeed-0.1, 1.0),0.0)
	EndIf
	
	TranslateEntity Players(playerID)\Collider, Cos(angle)*Players(playerID)\CurrSpeed * FPSfactor, 0, Sin(angle)*Players(playerID)\CurrSpeed * FPSfactor, True
	
	Local CollidedFloor% = False
	For j = 1 To CountCollisions(Players(playerID)\Collider)
		If CollisionY(Players(playerID)\Collider, j) < EntityY(Players(playerID)\Collider) - 0.25 Then CollidedFloor = True
	Next
	
	If CollidedFloor = True Then
		If Players(playerID)\DropSpeed# < - 0.07 Then 
			If CurrStepSFX=0 Then
				PlaySound_Strict(StepSFX(GetStepSound(Players(playerID)\Collider), 0, Rand(0, 7)))
			EndIf
			PlayerSoundVolume = Max(3.0,PlayerSoundVolume)
		EndIf
		Players(playerID)\DropSpeed# = 0
	Else
		Players(playerID)\DropSpeed# = Min(Max(Players(playerID)\DropSpeed - 0.006 * FPSfactor, -2.0), 0.0)
	EndIf
	PlayerFallingPickDistance# = 10.0
	
	TranslateEntity Players(playerID)\Collider, 0, Players(playerID)\DropSpeed * FPSfactor, 0
	
	UpdatePlayerUtils(playerID)
	PositionEntity Players(playerID)\GunPivot,Players(playerID)\X,Players(playerID)\Y+0.6+(Players(playerID)\CrouchState*-0.3),Players(playerID)\Z,True
	
	CatchErrors("Uncaught (UpdatePlayerPosition (" + playerID + "))")
End Function

Function UpdateSpectatorPosition(playerID%)
	Local co.Controller = First Controller
	Local mpl.MainPlayer = First MainPlayer
	Local temp#, speed# = 0.018
	
	If (Not co\Enabled)
		If (KeyDown(KEY_SPRINT)) Then 
			speed = speed * 2.5
		ElseIf KeyDown(KEY_CROUCH)
			speed = speed * 0.5
		EndIf
	Else
		If Players(playerID)\IsPlayerSprinting
			If JoyDown(CK_Sprint)
				speed = speed * 2.5
			ElseIf JoyDown(CK_Crouch)
				speed = speed * 0.5
			EndIf
		EndIf
	EndIf
	
	RotateEntity Players(playerID)\Collider, WrapAngle(EntityPitch(mpl\CameraPivot)), WrapAngle(EntityYaw(mpl\CameraPivot)), 0
	
	temp = speed * NoClipSpeed
	
	;[CONTROLLER]
	If (Not co\Enabled)
		If KeyDown(KEY_DOWN) Then MoveEntity Players(playerID)\Collider, 0, 0, -temp*FPSfactor
		If KeyDown(KEY_UP) Then MoveEntity Players(playerID)\Collider, 0, 0, temp*FPSfactor
		
		If KeyDown(KEY_LEFT) Then MoveEntity Players(playerID)\Collider, -temp*FPSfactor, 0, 0
		If KeyDown(KEY_RIGHT) Then MoveEntity Players(playerID)\Collider, temp*FPSfactor, 0, 0
	Else
		If GetLeftAnalogStickPitch()<0.0
			MoveEntity Players(playerID)\Collider, 0, 0, -temp*FPSfactor
		EndIf
		If GetLeftAnalogStickPitch()>0.0
			MoveEntity Players(playerID)\Collider, 0, 0, temp*FPSfactor
		EndIf
		If GetLeftAnalogStickYaw(True)<0.0
			MoveEntity Players(playerID)\Collider, -temp*FPSfactor, 0, 0
		EndIf
		If GetLeftAnalogStickYaw(True)>0.0
			MoveEntity Players(playerID)\Collider, temp*FPSfactor, 0, 0
		EndIf
	EndIf
	
	ResetEntity Players(playerID)\Collider
	
End Function

Function UpdatePlayerUtils(playerID%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	PositionEntity Players(playerID)\obj_lower,Players(playerID)\X,Players(playerID)\Y+0.1,Players(playerID)\Z,True
	If Players(playerID)\CurrHP > 0 Then
		RotateEntity Players(playerID)\obj_lower,0,Players(playerID)\Yaw,0,True
	EndIf
;	If playerID	<> mp_I\PlayerID Then
;		If HUDenabled And Players(playerID)\Team = Players(mp_I\PlayerID)\Team Then
;			PositionEntity Players(playerID)\NameTag,Players(playerID)\X,Players(playerID)\Y+0.8+(Players(playerID)\CrouchState*-0.3),Players(playerID)\Z,True
;			ShowEntity Players(playerID)\NameTag
;		Else
;			HideEntity Players(playerID)\NameTag
;		EndIf
;	Else
;		HideEntity Players(playerID)\NameTag
;	EndIf
	
End Function

Function GetPlayerPositions()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local i%
	
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			Players(i)\X = EntityX(Players(i)\Collider)
			Players(i)\Y = EntityY(Players(i)\Collider)
			Players(i)\Z = EntityZ(Players(i)\Collider)
		EndIf
	Next
	
End Function

Function MouseLookServer()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local co.Controller = First Controller
	Local g_I.GunInstance = First GunInstance
	Local i%
	Local g.Guns,currGun.Guns
	Local mpl.MainPlayer = First MainPlayer
	
	CameraShake = Max(CameraShake - (FPSfactor / 10), 0)
	
	For g.Guns = Each Guns
		If g\ID = g_I\HoldingGun Then
			If g\GunType<>GUNTYPE_MELEE Then
				currGun = g
			EndIf
			Exit
		EndIf
	Next
	
	Local IronSight_AddFOV# = 0.0
	If currGun <> Null Then
		IronSight_AddFOV = Abs(EntityX(IronSightPivot2%)/currGun\IronSightCoords\x)*0.2
	EndIf
	
	CameraZoom(Camera, (Min(1.0+(CurrCameraZoom/400.0),1.1) + IronSight_AddFOV) / (Tan((2*ATan(Tan((FOV#)/2)*(Float(RealGraphicWidth)/Float(RealGraphicHeight))))/2.0)))
	CurrCameraZoom = Max(CurrCameraZoom - FPSfactor, 0)
	
	If IsNaN(EntityX(Players(mp_I\PlayerID)\Collider)) Then
		
		PositionEntity Players(mp_I\PlayerID)\Collider, EntityX(Camera, True), EntityY(Camera, True) - 0.5, EntityZ(Camera, True), True
		Msg = "EntityX(Players(mp_I\PlayerID)\Collider) = NaN, RESETTING COORDINATES    -    New coordinates: "+EntityX(Players(mp_I\PlayerID)\Collider)
		MsgTimer = 300
	EndIf
	
	Local up# = (Sin(Shake) / (20.0+Players(mp_I\PlayerID)\CrouchState*20.0))*0.6	
	Local roll# = Max(Min(Sin(Shake/2)*0.625,8.0),-8.0)
	
	;PositionEntity Camera, EntityX(Players(mp_I\PlayerID)\Collider),EntityY(Players(mp_I\PlayerID)\Collider),EntityZ(Players(mp_I\PlayerID)\Collider)
	;RotateEntity Camera, 0, EntityYaw(Players(mp_I\PlayerID)\Collider), (roll*0.5)*0.5
	;
	;MoveEntity Camera, side, (up*0.5) + 0.6 + Players(mp_I\PlayerID)\CrouchState * -0.3, 0
	
	If (Players(mp_I\PlayerID)\CurrHP > 0) Lor (mp_I\SpectatePlayer = -1) Then
		PositionEntity mpl\CameraPivot, EntityX(Players(mp_I\PlayerID)\Collider),EntityY(Players(mp_I\PlayerID)\Collider),EntityZ(Players(mp_I\PlayerID)\Collider)
		RotateEntity mpl\CameraPivot, 0, EntityYaw(Players(mp_I\PlayerID)\Collider), (roll*0.5)*0.5
		MoveEntity mpl\CameraPivot, side, (up*0.5) + 0.6 + Players(mp_I\PlayerID)\CrouchState * -0.3, 0
	Else
		PositionEntity mpl\CameraPivot, EntityX(Players(mp_I\SpectatePlayer)\Collider),EntityY(Players(mp_I\SpectatePlayer)\Collider),EntityZ(Players(mp_I\SpectatePlayer)\Collider)
		RotateEntity mpl\CameraPivot, 0, EntityYaw(Players(mp_I\PlayerID)\Collider), 0
	EndIf
	
	If (Not MenuOpen) And (Not ConsoleOpen) And (Not InTeamSelection()) Then ;And (Not Players(mp_I\PlayerID)\Disable) Then
		; -- Update the smoothing que To smooth the movement of the mouse.
		If (Not co\Enabled) Then
			mouse_x_speed_1# = CurveValue(MouseXSpeed() * (MouseSens + 0.6) , mouse_x_speed_1, 6.0 / (MouseSens + 1.0))
		Else
			;[CONTROLLER]
			If GetRightAnalogStickYaw(True)<>0.0 Then
				mouse_x_speed_1# = CurveValue(GetRightAnalogStickYaw() * ((co\Sensitivity+0.6)*10*FPSfactor), mouse_x_speed_1, 6.0 / ((co\Sensitivity+1.0)*10*FPSfactor))
			Else
				mouse_x_speed_1# = CurveValue(0.0, mouse_x_speed_1, 6.0 / ((co\Sensitivity+1.0)*10*FPSfactor))
			EndIf
		EndIf
		If IsNaN(mouse_x_speed_1) Then mouse_x_speed_1 = 0
		
		If (Not co\Enabled) Then
			If InvertMouse Then
				mouse_y_speed_1# = CurveValue(-MouseYSpeed() * (MouseSens + 0.6), mouse_y_speed_1, 6.0/(MouseSens+1.0)) 
			Else
				mouse_y_speed_1# = CurveValue(MouseYSpeed () * (MouseSens + 0.6), mouse_y_speed_1, 6.0/(MouseSens+1.0)) 
			EndIf
		Else
			;[CONTROLLER]
			If Int(GetRightAnalogStickPitch(True))<>0 Then
				mouse_y_speed_1# = CurveValue(GetRightAnalogStickPitch(False,InvertMouse) * ((co\Sensitivity+0.6)*10*FPSfactor), mouse_y_speed_1, 6.0/((co\Sensitivity+1.0)*10*FPSfactor))
			Else
				mouse_y_speed_1# = CurveValue(0.0, mouse_y_speed_1, 6.0/((co\Sensitivity+1.0)*10*FPSfactor))
			EndIf
		EndIf
		If IsNaN(mouse_y_speed_1) Then mouse_y_speed_1 = 0
		
		Local the_yaw# = ((mouse_x_speed_1#)) * mouselook_x_inc#
		Local the_pitch# = ((mouse_y_speed_1#)) * mouselook_y_inc#
		
		TurnEntity Players(mp_I\PlayerID)\Collider, 0.0, -the_yaw#, 0.0 ; Turn the user on the Y (yaw) axis.		
		user_camera_pitch# = user_camera_pitch# + the_pitch#
		; -- Limit the user;s camera To within 180 degrees of pitch rotation. ;EntityPitch(); returns useless values so we need To use a variable To keep track of the camera pitch.
		If user_camera_pitch# > 70.0 Then user_camera_pitch# = 70.0
		If user_camera_pitch# < - 70.0 Then user_camera_pitch# = -70.0
	EndIf
	
	;RotateEntity Camera, WrapAngle(user_camera_pitch + Rnd(-CameraShake, CameraShake)),WrapAngle(EntityYaw(Players(mp_I\PlayerID)\Collider) + Rnd(-CameraShake, CameraShake)),roll ; Pitch the user;s camera up And down.
	RotateEntity mpl\CameraPivot, WrapAngle(user_camera_pitch),WrapAngle(EntityYaw(Players(mp_I\PlayerID)\Collider)),roll
	RotateEntity Camera,Rnd(-CameraShake, CameraShake),Rnd(-CameraShake, CameraShake),0.0
	
	If ParticleAmount=2 Then
		If Rand(35) = 1 Then
			Local pvt% = CreatePivot()
			PositionEntity(pvt, EntityX(mpl\CameraPivot, True), EntityY(mpl\CameraPivot, True), EntityZ(mpl\CameraPivot, True))
			RotateEntity(pvt, 0, Rnd(360), 0)
			If Rand(2) = 1 Then
				MoveEntity(pvt, 0, Rnd(-0.5, 0.5), Rnd(0.5, 1.0))
			Else
				MoveEntity(pvt, 0, Rnd(-0.5, 0.5), Rnd(0.5, 1.0))
			EndIf
			
			Local p.Particles = CreateParticle(EntityX(pvt), EntityY(pvt), EntityZ(pvt), 2, 0.002, 0, 300)
			p\speed = 0.001
			RotateEntity(p\pvt, Rnd(-20, 20), Rnd(360), 0)
			
			p\SizeChange = -0.00001
			
			FreeEntity pvt
		EndIf
	EndIf
	
	; -- Limit the mouse;s movement. Using this method produces smoother mouselook movement than centering the mouse Each loop.
	If (Not MenuOpen) And (Not ConsoleOpen) And (Not InTeamSelection()) Then
		If (MouseX() > mouse_right_limit) Lor (MouseX() < mouse_left_limit) Lor (MouseY() > mouse_bottom_limit) Lor (MouseY() < mouse_top_limit) Then
			MoveMouse viewport_center_x, viewport_center_y
		EndIf
	EndIf
	
End Function

Function DrawGUIMP()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local fo.Fonts = First Fonts
	Local i%,x%,y%,j%
	Local yawvalue#,pitchvalue#
	Local mi.MenuImages = First MenuImages
	Local mpl.MainPlayer = First MainPlayer
	Local g.Guns
	
	If MenuOpen Lor ConsoleOpen Lor InTeamSelection() Then
		ShowPointer()
	Else
		HidePointer()
	EndIf
	
	If ClosestItem <> Null Then
		If ClosestItem\collider <> 0 Then
			yawvalue# = -DeltaYaw(mpl\CameraPivot, ClosestItem\collider)
			If yawvalue > 90 And yawvalue <= 180 Then yawvalue = 90
			If yawvalue > 180 And yawvalue < 270 Then yawvalue = 270
			pitchvalue# = -DeltaPitch(mpl\CameraPivot, ClosestItem\collider)
			If pitchvalue > 90 And pitchvalue <= 180 Then pitchvalue = 90
			If pitchvalue > 180 And pitchvalue < 270 Then pitchvalue = 270
			
			DrawImage(HandIcon2, opt\GraphicWidth / 2 + Sin(yawvalue) * (opt\GraphicWidth / 3) - 32, opt\GraphicHeight / 2 - Sin(pitchvalue) * (opt\GraphicHeight / 3) - 32)
		EndIf
	EndIf
	
	If DrawHandIcon Then DrawImage(HandIcon, opt\GraphicWidth / 2 - 32, opt\GraphicHeight / 2 - 32)
	For i = 0 To 3
		If DrawArrowIcon(i) Then
			x = opt\GraphicWidth / 2 - 32
			y = opt\GraphicHeight / 2 - 32		
			Select i
				Case 0
					y = y - 64 - 5
				Case 1
					x = x + 64 + 5
				Case 2
					y = y + 64 + 5
				Case 3
					x = x - 5 - 64
			End Select
			DrawImage(HandIcon, x, y)
			Color 0, 0, 0
			Rect(x + 4, y + 4, 64 - 8, 64 - 8)
			DrawImage(mi\ArrowIMG[i], x + 21, y + 21)
			DrawArrowIcon(i) = False
		EndIf
	Next
	
	Local plAmount%
	If HUDenabled Then
		Local width% = 204, height% = 20
		;Health Bar
		x% = 80
		y% = opt\GraphicHeight - 95
		
		If Players(mp_I\PlayerID)\CurrHP>0 Then
			Color 255,255,255
		Else
			Color 255,0,0
		EndIf
		Rect(x, y, width, height, False)
		
		For i = 1 To Int(((width - 2) * (Players(mp_I\PlayerID)\CurrHP / 100.0)) / 10)
			DrawImage(BlinkMeterIMG, x + 3 + 10 * (i - 1), y + 3)
		Next
		
		Color 0, 0, 0
		Rect(x - 50, y, 30, 30)
		
		If Players(mp_I\PlayerID)\CurrHP>20 Then
			Color 255, 255, 255
		Else
			Color 255,0,0
		EndIf
		Rect(x - 50 - 1, y - 1, 30 + 2, 30 + 2, False)
		
		DrawImage mp_I\HealthIcon, x - 50, y
		
		;Stamina Bar
		If Players(mp_I\PlayerID)\CurrStamina < 100.0 Then
			y = opt\GraphicHeight - 55
			x = (opt\GraphicWidth / 2) - (width / 2) + 20
			If Players(mp_I\PlayerID)\CurrStamina <= 20.0 Then
				Color 255, 0, 0
			Else
				Color 255, 255, 255
			EndIf
			Rect (x, y, width, height, False)
			For i = 1 To Int(((width - 2) * (Players(mp_I\PlayerID)\CurrStamina / 100.0)) / 10)
				DrawImage(StaminaMeterIMG, x + 3 + 10 * (i - 1), y + 3)
			Next
			
			Color 0, 0, 0
			Rect(x - 50, y, 30, 30)
			
			If Players(mp_I\PlayerID)\CurrStamina <= 0.0 Then
				Color 255, 0, 0
			Else
				Color 255, 255, 255
			EndIf
			Rect(x - 50 - 1, y - 1, 30 + 2, 30 + 2, False)
			If Players(mp_I\PlayerID)\Crouch Then
				DrawImage CrouchIcon, x - 50, y
			Else
				DrawImage SprintIcon, x - 50, y
			EndIf
		EndIf
		
		;Kevlar Bar
		x = 80
		y = opt\GraphicHeight - 55
		
		If Players(mp_I\PlayerID)\CurrKevlar>0 Then
			Color 255,255,255
		Else
			Color 255,0,0
		EndIf
		Rect(x, y, width, height, False)
		
		Color 0,0,0
		Rect(x - 50, y, 30, 30)
		
		If Players(mp_I\PlayerID)\CurrKevlar>20 Then
			Color 255, 255, 255
		Else
			Color 255,0,0
		EndIf
		Rect(x - 50 - 1, y - 1, 30 + 2, 30 + 2, False)
		DrawImage KevlarIcon, x - 50, y
		For i = 1 To Int(Players(mp_I\PlayerID)\CurrKevlar/5)
			DrawImage(BlinkMeterIMG, x + 3 + 10 * (i - 1), y + 3)
		Next
		
		If Players(mp_I\PlayerID)\CurrKevlar>100 Then
			Color 255,255,0
			Rect(x, y+height+5, (width/2)+2, height, False)
			
			For i = 1 To Int((Players(mp_I\PlayerID)\CurrKevlar-100)/5)
				DrawImage(ExtraKevlarIMG, x + 3 + 10 * (i - 1), (y+height+5) + 3)
			Next
		EndIf
		
		;Gun Stuff
		DrawMPGunsInHud()
		
		x = 55
		y = 55
		width = 64
		height = 64
		If mpl\SlotsDisplayTimer > 0.0 Then
			For i = 0 To MaxSlots-1
				DrawFrame((x-3)+(128*i),y-3,width+6,height+6)
				If i = Players(mp_I\PlayerID)\SelectedSlot Then
					Color 0,255,0
					Rect (x-3)+(128*i),y-3,width+6,height+6,True
				EndIf
				If Players(mp_I\PlayerID)\WeaponInSlot[i]<>GUN_UNARMED Then
					For g = Each Guns
						If g\ID = Players(mp_I\PlayerID)\WeaponInSlot[i] Then
							DrawImage g\IMG,x+(128*i),y
							Color 255,255,255
							If i = Players(mp_I\PlayerID)\SelectedSlot Then
								AASetFont fo\Font[Font_Default]
								AAText(x+(width/2)+(128*i),y+height+10,g\DisplayName,True,False)
							EndIf
							Exit
						EndIf
					Next
				EndIf
			Next
		EndIf
		
		;Boss HP bar
		If mp_I\BossNPC <> Null Then
			If mp_I\BossNPC\HP > 0 Then
				x = (opt\GraphicWidth / 2) - 202
				y = 50
				width = 404
				height = 20
				
				Color 255,255,255
				Rect(x, y, width, height, False)
				
				Color 255,0,0
				AASetFont fo\Font[Font_Digital_Medium]
				AAText opt\GraphicWidth/2,15,mp_I\BossNPC\NVName,True,False
				Color 255,255,255
				
				For i = 1 To Int(((width - 2) * (mp_I\BossNPC\HP / Float(mp_I\MaxBossHealth))) / 10)
					DrawImage(BlinkMeterIMG, x + 3 + 10 * (i - 1), y + 3)
				Next
			EndIf
		EndIf
		
		Select mp_I\Gamemode\ID
			Case Gamemode_Waves
				;[Block]
				x = opt\GraphicWidth - 50
				y = 50
				If (mp_I\Gamemode\Phase Mod 2) = 1 Then
					Color 0,255,0
					AASetFont fo\Font[Font_Digital_Medium]
					If Int(Floor((mp_I\Gamemode\PhaseTimer+35)/70.0)) < 10 Then
						AAText x,y,"0:0"+Int(Floor((mp_I\Gamemode\PhaseTimer+35)/70.0)),2,0
					Else
						AAText x,y,"0:"+Int(Floor((mp_I\Gamemode\PhaseTimer+35)/70.0)),2,0
					EndIf	
				ElseIf (mp_I\Gamemode\Phase > 0) Then
					If mp_I\Gamemode\PhaseTimer > 0 Then
						Color 0,Min(mp_I\Gamemode\PhaseTimer,127.5)*2,0
						AASetFont fo\Font[Font_Digital_Large]
						AAText opt\GraphicWidth/2,y*2,"WAVE "+(mp_I\Gamemode\Phase/2),1,0
					EndIf
					Color 0,255,0
					AASetFont fo\Font[Font_Digital_Medium]
					AAText x,y,"X "+mp_I\Gamemode\EnemyCount,2,1
					DrawImage mp_I\Gamemode\img[0],x-100,y
					AAText x,y+60,"WAVE "+(mp_I\Gamemode\Phase/2)+"/"+(mp_I\Gamemode\MaxPhase),2,1
				EndIf
				;[End Block]
			Case Gamemode_Deathmatch
				;[Block]
				x = opt\GraphicWidth / 2
				y = opt\GraphicHeight / 2
				If mp_I\Gamemode\Phase > Deathmatch_Game Then
					Color 255,255,255
					AASetFont fo\Font[Font_Default_Large]
					If mp_I\Gamemode\Phase = Deathmatch_MTFLost Then
						AAText x,y,"Chaos Insurgency wins",True,True
					Else
						AAText x,y,"Mobile Task Force wins",True,True
					EndIf
				EndIf
				x = opt\GraphicWidth - 50
				y = 50
				If mp_I\Gamemode\PhaseTimer > 0 Then
					Color 0,255,0
					AASetFont fo\Font[Font_Digital_Medium]
					If Int(Floor((mp_I\Gamemode\PhaseTimer+35)/70.0)) < 10 Then
						AAText x,y+40,"0:0"+Int(Floor((mp_I\Gamemode\PhaseTimer+35)/70.0)),2,0
					Else
						AAText x,y+40,"0:"+Int(Floor((mp_I\Gamemode\PhaseTimer+35)/70.0)),2,0
					EndIf
				EndIf
				Color 0,255,0
				AASetFont fo\Font[Font_Digital_Medium]
				AAText x,y,"X "+Players(mp_I\PlayerID)\Kills,2,1
				DrawImage mp_I\Gamemode\img[2],x-100,y
				;[End Block]
		End Select
		
		;ToggleGuns()
		
		If DebugHUD Then
			Color 255, 255, 255
			AASetFont fo\ConsoleFont
			
			AAText x - 50, 50, "Player Position: (" + f2s(EntityX(Players(mp_I\PlayerID)\Collider), 3) + ", " + f2s(EntityY(Players(mp_I\PlayerID)\Collider), 3) + ", " + f2s(EntityZ(Players(mp_I\PlayerID)\Collider), 3) + ")"
			AAText x - 50, 70, "Camera Position: (" + f2s(EntityX(mpl\CameraPivot), 3)+ ", " + f2s(EntityY(mpl\CameraPivot), 3) +", " + f2s(EntityZ(mpl\CameraPivot), 3) + ")"
			AAText x - 50, 100, "Player Rotation: (" + f2s(EntityPitch(Players(mp_I\PlayerID)\Collider), 3) + ", " + f2s(EntityYaw(Players(mp_I\PlayerID)\Collider), 3) + ", " + f2s(EntityRoll(Players(mp_I\PlayerID)\Collider), 3) + ")"
			AAText x - 50, 120, "Camera Rotation: (" + f2s(EntityPitch(mpl\CameraPivot), 3)+ ", " + f2s(EntityYaw(mpl\CameraPivot), 3) +", " + f2s(EntityRoll(mpl\CameraPivot), 3) + ")"
			
			AAText x - 50, 300, "Stamina: " + f2s(Players(mp_I\PlayerID)\CurrStamina, 3)
			
			AAText x + 350, 110, "Triangles rendered: "+CurrTrisAmount
			AAText x + 350, 130, "Active textures: "+ActiveTextures()
			
			AASetFont fo\Font[Font_Default]
		EndIf
		Color 255, 255, 255
		AASetFont fo\ConsoleFont
		AAText opt\GraphicWidth-110,20,"Ping: "+Int(Players(mp_I\PlayerID)\Ping)+" ms"
	EndIf
	
	;HUD Stuff that will still be drawn when the HUD is disabled
	Select mp_I\Gamemode\ID
		Case Gamemode_Deathmatch
			;[Block]
			plAmount = 0
			x = opt\GraphicWidth / 2
			y = opt\GraphicHeight / 2
			;TODO - Make this code a bit more optimized when needed
			If InTeamSelection() Then
				Color 255,255,255
				DrawFrame x - 400 * MenuScale,y - 400 * MenuScale, 800 * MenuScale, 800 * MenuScale
				AASetFont fo\Font[Font_Default_Large]
				AAText x, y - 365 * MenuScale, "Select the team", True, True
				AASetFont fo\Font[Font_Default_Medium]
				;MTF team
				DrawFrame x - 328 * MenuScale, y - 328 * MenuScale, 306 * MenuScale, 306 * MenuScale
				DrawFrame x - 328 * MenuScale, y - 25 * MenuScale, 306 * MenuScale, 56 * MenuScale
				plAmount = 0
				For i = 0 To (mp_I\MaxPlayers-1)
					If Players(i)<>Null Then
						If Players(i)\Team = Team_MTF Then
							plAmount = plAmount + 1
						EndIf
					EndIf
				Next
				AAText x - 175 * MenuScale, y + 3 * MenuScale, "MTF: "+plAmount, True, True
				DrawFrame x - 328 * MenuScale, y + 28 * MenuScale, 306 * MenuScale, 320 * MenuScale
				i = 0
				For j = 0 To (mp_I\MaxPlayers-1)
					If Players(j)<>Null Then
						If Players(j)\Team = Team_MTF Then
							AAText x - 175 * MenuScale, y + (20 * MenuScale) + (48*(i+1)*MenuScale), Players(j)\Name, True, True
							i = i + 1
						EndIf
					EndIf
				Next
				If MouseOn(x - 325 * MenuScale, y - 325 * MenuScale, ImageWidth(mp_I\Gamemode\img[Team_MTF-1]), ImageHeight(mp_I\Gamemode\img[Team_MTF-1])) Then
					If i < Ceil(mp_I\MaxPlayers/2) Then
						Color 0,255,0
					Else
						Color 255,0,0
					EndIf
					Rect x - 328 * MenuScale, y - 328 * MenuScale, ImageWidth(mp_I\Gamemode\img[Team_MTF-1])+6*MenuScale, ImageHeight(mp_I\Gamemode\img[Team_MTF-1])+6*MenuScale, True
					Color 255,255,255
				EndIf
				DrawImage mp_I\Gamemode\img[Team_MTF-1], x - 325 * MenuScale, y - 325 * MenuScale
				;CI team
				DrawFrame x + 22 * MenuScale, y - 328 * MenuScale, 306 * MenuScale, 306 * MenuScale
				DrawFrame x + 22 * MenuScale, y - 25 * MenuScale, 306 * MenuScale, 56 * MenuScale
				plAmount = 0
				For i = 0 To (mp_I\MaxPlayers-1)
					If Players(i)<>Null Then
						If Players(i)\Team = Team_CI Then
							plAmount = plAmount + 1
						EndIf
					EndIf
				Next
				AAText x + 175 * MenuScale, y + 3 * MenuScale, "CI: "+plAmount, True, True
				DrawFrame x + 22 * MenuScale, y + 28 * MenuScale, 306 * MenuScale, 320 * MenuScale
				i = 0
				For j = 0 To (mp_I\MaxPlayers-1)
					If Players(j)<>Null Then
						If Players(j)\Team = Team_CI Then
							AAText x + 175 * MenuScale, y + (20 * MenuScale) + (48*(i+1)*MenuScale), Players(j)\Name, True, True
							i = i + 1
						EndIf
					EndIf
				Next
				If MouseOn(x + 25 * MenuScale, y - 325 * MenuScale, ImageWidth(mp_I\Gamemode\img[Team_CI-1]), ImageHeight(mp_I\Gamemode\img[Team_CI-1])) Then
					If i < Ceil(mp_I\MaxPlayers/2) Then
						Color 0,255,0
					Else
						Color 255,0,0
					EndIf
					Rect x + 22 * MenuScale, y - 328 * MenuScale, ImageWidth(mp_I\Gamemode\img[Team_CI-1])+6*MenuScale, ImageHeight(mp_I\Gamemode\img[Team_CI-1])+6*MenuScale, True
					Color 255,255,255
				EndIf
				DrawImage mp_I\Gamemode\img[Team_CI-1], x + 25 * MenuScale, y - 325 * MenuScale
			EndIf
			;[End Block]
	End Select
	
	If InTeamSelection() Then
		If opt\DisplayMode=2 Then DrawImage CursorIMG, ScaledMouseX(),ScaledMouseY()
	EndIf
	
End Function

Function UpdateGUIMP()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local g_I.GunInstance = First GunInstance
	Local mpl.MainPlayer = First MainPlayer
	
	Local x%,y%,i%,temp#
	Local plAmount%
	Local g.Guns
	
	Select mp_I\Gamemode\ID
		Case Gamemode_Deathmatch
			;[Block]
			x = opt\GraphicWidth / 2
			y = opt\GraphicHeight / 2
			;TODO: Add the ability to select it with controller
			;TODO: Make it so the server needs to verify if the player can join the team
			;TODO: When more guns are implemented, then there should be a function to reset their shooting states properly
			If Players(mp_I\PlayerID)\Team = Team_Unknown Then
				If MouseHit1 Then
					;MTF
					If MouseOn(x - 325 * MenuScale, y - 325 * MenuScale, ImageWidth(mp_I\Gamemode\img[Team_MTF-1]), ImageHeight(mp_I\Gamemode\img[Team_MTF-1])) Then
						plAmount = 0
						For i = 0 To (mp_I\MaxPlayers-1)
							If Players(i)<>Null Then
								If Players(i)\Team = Team_MTF Then
									plAmount = plAmount + 1
								EndIf
							EndIf
						Next
						If plAmount < Ceil(mp_I\MaxPlayers/2) Then
							Players(mp_I\PlayerID)\Team = Team_MTF
							ResetControllerSelections()
							ResetInput()
							MouseXSpeed() : MouseYSpeed() : MouseZSpeed() : mouse_x_speed_1#=0.0 : mouse_y_speed_1#=0.0
							For g.Guns = Each Guns
								If g\name$ = "p90" Then
									g\MouseDownTimer# = 15.0
								EndIf
							Next
							SetupTeam(mp_I\PlayerID)
						EndIf
					EndIf
					;CI
					If MouseOn(x + 25 * MenuScale, y - 325 * MenuScale, ImageWidth(mp_I\Gamemode\img[Team_CI-1]), ImageHeight(mp_I\Gamemode\img[Team_CI-1])) Then
						plAmount = 0
						For i = 0 To (mp_I\MaxPlayers-1)
							If Players(i)<>Null Then
								If Players(i)\Team = Team_CI Then
									plAmount = plAmount + 1
								EndIf
							EndIf
						Next
						If plAmount < Ceil(mp_I\MaxPlayers/2) Then
							Players(mp_I\PlayerID)\Team = Team_CI
							ResetControllerSelections()
							ResetInput()
							MouseXSpeed() : MouseYSpeed() : MouseZSpeed() : mouse_x_speed_1#=0.0 : mouse_y_speed_1#=0.0
							For g.Guns = Each Guns
								If g\name$ = "p90" Then
									g\MouseDownTimer# = 15.0
								EndIf
							Next
							SetupTeam(mp_I\PlayerID)
						EndIf
					EndIf
				EndIf
			EndIf
			;[End Block]
	End Select
	If Players(mp_I\PlayerID)\CurrHP > 0.0 Then
		For i = 0 To MaxSlots-1
			If KeyHit(i+2) Then
				If Players(mp_I\PlayerID)\WeaponInSlot[i]<>GUN_UNARMED Then
					If i<>Players(mp_I\PlayerID)\SelectedSlot Then
						If mp_I\PlayState = GAME_SERVER Then
							Players(mp_I\PlayerID)\SelectedSlot = i
							g_I\GunChangeFLAG = False
						Else
							Players(mp_I\PlayerID)\WantsSlot = i
						EndIf
						mpl\SlotsDisplayTimer = 70*3
					EndIf
				EndIf
			EndIf
		Next
	EndIf
	mpl\SlotsDisplayTimer = Max(mpl\SlotsDisplayTimer-FPSfactor,0.0)
	
	If InteractHit(1,CK_Pause) And EndingTimer = 0 Then
		If MenuOpen Lor InvOpen Then
			If OptionsMenu <> 0 Then SaveOptionsINI()
			MouseXSpeed() : MouseYSpeed() : MouseZSpeed() : mouse_x_speed_1#=0.0 : mouse_y_speed_1#=0.0
			DeleteMenuGadgets()
		EndIf
		MenuOpen = (Not MenuOpen)
		
		AchievementsMenu = 0
		OptionsMenu = 0
		QuitMSG = 0
	EndIf
	
End Function

Function RecvDataServer()
	CatchErrors("RecvDataServer")
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local getconn,TempRead$,TempID
	Local hasClosestItem%,ItemID%,IsItemPicked%,it.Items
	Local sprayX#,sprayY#,sprayZ#,sprayPitch#,sprayYaw#
	Local plTeam%
	Local sslot%
	Local prevTeam%, prevusername$
	
	Local currMSGSync%
	Local currPing%
	
	Local i%
	
	;Receiving data from clients
	getconn = RecvUDPMsg(mp_I\Server)
	While getconn
		currMSGSync = ReadByte(mp_I\Server)
		
		If currMSGSync = PACKET_LOAD Lor currMSGSync = PACKET_AUTHORIZE Then
			;[Block]
			Local giveID% = CheckForConnectingPlayer(getconn, currMSGSync, 1)
			If giveID > -1 Then
				CreatePlayer(giveID)
				RespawnPlayer(giveID)
			EndIf
			getconn = RecvUDPMsg(mp_I\Server)
			;[End Block]
		Else
			TempID = ReadByte(mp_I\Server)
			If Players(TempID) = Null Then
				RuntimeError "Error: TempID "+TempID+" received but doesn't match any existing player!"
			EndIf
			If Players(TempID)\FinishedLoading Then
				Select currMSGSync
					Case PACKET_PLAYER
						;[Block]
						If Players(TempID)<>Null Then
							If Players(TempID)\IP = getconn Then
								Players(TempID)\Connected = True
								
								prevusername = Players(i)\Name
								Players(i)\Name = ReadLine(mp_I\Server)
								If prevusername <> Players(i)\Name Then
									FreeEntity Players(i)\NameTag
									CreatePlayerTag(i)
								EndIf
								
								Players(TempID)\Yaw = ReadFloat(mp_I\Server)
								Players(TempID)\Pitch = ReadFloat(mp_I\Server)
								Players(TempID)\walkangle = ((ReadByte(mp_I\Server)/255.0)*360.0)
								Local prevSlot = Players(TempID)\SelectedSlot
								Players(TempID)\SelectedSlot = ReadByte(mp_I\Server)
								
								Local prevWalking = Players(TempID)\walking
								Local byte% = ReadByte(mp_I\Server)
								Players(TempID)\walking = ((byte Shr 0) Mod 2)
								Players(TempID)\PressSprint = ((byte Shr 1) Mod 2)
								Players(TempID)\Crouch = ((byte Shr 2) Mod 2)
								Players(TempID)\IronSight = ((byte Shr 3) Mod 2)
								Players(TempID)\PressMouse1 = ((byte Shr 4) Mod 2)
								Players(TempID)\PressReload = ((byte Shr 5) Mod 2)
								
								Players(TempID)\CurrChunk = ReadByte(mp_I\Server)
								
								hasClosestItem = ReadByte(mp_I\Server)
								If hasClosestItem Then
									ItemID = ReadInt(mp_I\Server)
									IsItemPicked = ReadByte(mp_I\Server)
									If IsItemPicked Then
										For it.Items = Each Items
											If it\ID = ItemID
												PickMPItem(it,TempID)
												Exit
											EndIf
										Next
									EndIf
								EndIf
								
								prevTeam = Players(TempID)\Team
								Players(TempID)\Team = ReadByte(mp_I\Server)-1
								If prevTeam <> Players(TempID)\Team Then
									SetupTeam(TempID)
								EndIf
								
								If Players(TempID)\SelectedSlot<>prevSlot Then
									Players(TempID)\DeployState = 0
									Players(TempID)\ReloadState = 0
									Players(TempID)\ShootState = 0
									Players(TempID)\PressMouse1 = False
									Players(TempID)\PressReload = False
								EndIf
								
								If Players(TempID)\walking <> prevWalking And Players(TempID)\walking Then
									UpdatePlayerPosition(TempID)
								EndIf
								
								Players(TempID)\LastMsgTime = MilliSecs()
								Players(TempID)\FinishedLoading = True
								
								currPing = ReadInt(mp_I\Server)
								If currPing = Players(TempID)\PingCheckValue Then
									Players(TempID)\Ping = MilliSecs()-Players(TempID)\LastPingMillisecs
								EndIf
							EndIf
						EndIf
						getconn = RecvUDPMsg(mp_I\Server)
						;[End Block]
					Case PACKET_QUIT
						;[Block]
						If Players(TempID) <> Null Then
							If Players(TempID)\IP = getconn Then
								mp_I\PlayerCount=mp_I\PlayerCount-1
								FreeEntity Players(TempID)\Collider
								FreeEntity Players(TempID)\obj_upper
								FreeEntity Players(TempID)\obj_lower
								FreeEntity Players(TempID)\NameTag
								Delete Players(TempID)
								Players(TempID)=Null
								If mp_I\SpectatePlayer = TempID Then
									FindPlayerToSpectate(1)
								EndIf
							EndIf
						EndIf
						getconn = RecvUDPMsg(mp_I\Server)
						;[End Block]
				End Select
			Else
				If currMSGSync Then
					Players(TempID)\FinishedLoading = True
					Players(TempID)\LastMsgTime = MilliSecs()
				EndIf
				getconn = RecvUDPMsg(mp_I\Server)
			EndIf
		EndIf
	Wend
	
	CatchErrors("Uncaught (RecvDataServer)")
End Function

Function RecvDataClient()
	CatchErrors("RecvDataClient")
	
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local g_I.GunInstance = First GunInstance
	
	Local getconn,TempRead$,i,ttmp,j
	Local temp%,found%
	Local it.Items,deleteitem%,itID%,itX#,itY#,itZ#,itName$,itTempName$,itYaw#,itFound.Items
	Local n.NPCs,deletenpc%,nID%,nX#,nY#,nZ#,ntype%,nYaw#,nFound.NPCs,nCurrSpeed#,nHP%,nDistTimer#
	Local gunAmmo1%,gunAmmo2%,reloadAmmo1%,reloadAmmo2%,sslot%,shootstate#,reloadstate#,deploystate#
	Local nState1#,nState2#,nState3#
	Local kevlar#,playDamageSFX%
	Local currsync%
	Local plTeam%
	Local prevWeaponInCurrSlot%
	Local prevTeam%
	
	Local byte%,x#,y#,z#
	Local pmp.ParticleMP,ptype%
	Local dx#,dy#,dz#,dpitch#,dyaw#,dnx#,dny#,dnz#
	
	Local hbX#,hbY#,hbZ#,hbB,hb.HitBox
	
	Local prevUsername$
	
	Local currMSGSync%
	
	getconn = RecvUDPMsg(mp_I\Server)
	While getconn ;the server has given you a message
		Players(0)\LastMsgTime = MilliSecs()
		Players(0)\FinishedLoading = True
		currMSGSync = ReadByte(mp_I\Server)
		Select currMSGSync
			Case PACKET_PING
				;[Block]
				Players(mp_I\PlayerID)\PingCheckValue = ReadInt(mp_I\Server)
				getconn = RecvUDPMsg(mp_I\Server)
				;[End Block]
			Case PACKET_PLAYER
				;[Block]
				For i=0 To (mp_I\MaxPlayers-1)
					ttmp% = ReadByte(mp_I\Server)
					If ttmp% Then
						If Players(i)=Null Then
							Players(i) = New Player
							CreatePlayer(i)
							mp_I\PlayerCount = mp_I\PlayerCount + 1
						EndIf
						
						prevTeam = Players(i)\Team
						Players(i)\Team = ReadByte(mp_I\Server)-1
						If prevTeam <> Players(i)\Team Then
							If Players(i)\Team >= Team_Spectator Then
								SetupTeam(i)
							Else
								Players(i)\Team = prevTeam
							EndIf
						EndIf
						
						If Players(i)\Team > Team_Spectator Then
							Players(i)\PrevShootState = Players(i)\ShootState
							
							If i<>mp_I\PlayerID Then
								prevUsername = Players(i)\Name
								Players(i)\Name = ReadLine(mp_I\Server)
								Players(i)\Yaw = ReadFloat(mp_I\Server)
								Players(i)\Pitch = ReadFloat(mp_I\Server)
								Players(i)\CurrSpeed = ReadFloat(mp_I\Server)
								Players(i)\walkangle = Float((ReadByte(mp_I\Server)/255.0)*360.0)
								Players(i)\ShootState = ReadFloat(mp_I\Server)
								Players(i)\ReloadState = ReadFloat(mp_I\Server)
								Players(i)\DeployState = ReadFloat(mp_I\Server)
								byte = ReadByte(mp_I\Server)
								
								RotateEntity Players(i)\Collider,Players(i)\Pitch,Players(i)\Yaw,0
								Players(i)\walking = ((byte Shr 0) Mod 2)
								Players(i)\IsPlayerSprinting = ((byte Shr 1) Mod 2)
								Players(i)\Crouch = ((byte Shr 2) Mod 2)
								Players(i)\IronSight = ((byte Shr 3) Mod 2)
								Players(i)\PressMouse1 = ((byte Shr 4) Mod 2)
								Players(i)\PressReload = ((byte Shr 5) Mod 2)
								
								Players(i)\CurrChunk = ReadByte(mp_I\Server)
								
								If prevUsername <> Players(i)\Name Then
									FreeEntity Players(i)\NameTag
									CreatePlayerTag(i)
								EndIf
							EndIf
							x# = ReadFloat(mp_I\Server)
							y# = ReadFloat(mp_I\Server)
							z# = ReadFloat(mp_I\Server)
							Players(i)\CurrStamina = ReadFloat(mp_I\Server)
							Players(i)\CrouchState = ReadFloat(mp_I\Server)
							Local prevHP# = Players(i)\CurrHP
							Players(i)\CurrHP = ReadFloat(mp_I\Server)
							Players(i)\CurrKevlar = ReadFloat(mp_I\Server)
							
							If i<>mp_I\PlayerID Then
								Players(i)\X = x
								Players(i)\Y = y
								Players(i)\Z = z
								PositionEntity Players(i)\Collider,Players(i)\X,Players(i)\Y,Players(i)\Z
								ResetEntity Players(i)\Collider
								
								UpdatePlayerUtils(i)
							Else
								If Abs(Players(i)\X-x) > 1.5 And Abs(Players(i)\Y-y) > 1.5 And Abs(Players(i)\Z-z) > 1.5 Then
									If mp_I\PositionSyncTimer <= 0.0 Then
										If Abs(Players(i)\X-x) > 0.02 Lor Abs(Players(i)\Y-y) > 0.02 Lor Abs(Players(i)\Z-z) > 0.02 Then
											Players(i)\X = CurveValue(EntityX(Players(i)\Collider), x, 20.0)
											Players(i)\Y = CurveValue(EntityY(Players(i)\Collider), y, 10.0)
											Players(i)\Z = CurveValue(EntityZ(Players(i)\Collider), z, 20.0)
											PositionEntity Players(i)\Collider,Players(i)\X,Players(i)\Y,Players(i)\Z
											Players(i)\DropSpeed = 0.0
										EndIf
										mp_I\PositionSyncTimer = 70*2
									Else
										mp_I\PositionSyncTimer = mp_I\PositionSyncTimer - FPSfactor
									EndIf
								Else
									Players(i)\X = x
									Players(i)\Y = y
									Players(i)\Z = z
									PositionEntity Players(i)\Collider,Players(i)\X,Players(i)\Y,Players(i)\Z
									ResetEntity Players(i)\Collider
									Players(i)\DropSpeed = 0.0
								EndIf
								
								If prevHP <= 0 And Players(i)\CurrHP > 0 Then
									Players(i)\WantsSlot = SLOT_SECONDARY
									Players(i)\SelectedSlot = SLOT_SECONDARY
									Players(i)\X = x
									Players(i)\Y = y
									Players(i)\Z = z
									PositionEntity Players(i)\Collider,Players(i)\X,Players(i)\Y,Players(i)\Z
									ResetEntity Players(i)\Collider
									Players(i)\DropSpeed = 0.0
									SetAnimTime Players(i)\obj_lower,0
									SetAnimTime Players(i)\obj_upper,0
									MouseHit(1)
									g_I\GunChangeFLAG = False
									RotateEntity Players(i)\Collider,0,0,0
									RotateEntity Camera,0,0,0
									Players(i)\Pitch = EntityPitch(Camera)
									Players(i)\Yaw = EntityYaw(Players(i)\Collider)
									
									If mp_I\PlayerID = i Then
										If Players(i)\Team = Team_CI Then
											mp_I\Map\CurrChunk = mp_I\MapInList\CISpawn-(mp_I\MapInList\ChunkStart-1)
										Else
											mp_I\Map\CurrChunk = mp_I\MapInList\NTFSpawn-(mp_I\MapInList\ChunkStart-1)
										EndIf
									EndIf
									
									If mp_I\Gamemode\ID = Gamemode_Deathmatch Then
										Players(i)\WantsSlot = SLOT_PRIMARY
										Players(i)\SelectedSlot = SLOT_PRIMARY
									EndIf
								EndIf
							EndIf
							
							Players(i)\Ammo[SLOT_PRIMARY] = ReadShort(mp_I\Server)
							Players(i)\Ammo[SLOT_SECONDARY] = ReadShort(mp_I\Server)
							Players(i)\ReloadAmmo[SLOT_PRIMARY] = ReadByte(mp_I\Server)
							Players(i)\ReloadAmmo[SLOT_SECONDARY] = ReadByte(mp_I\Server)
							Local prevSlot = Players(i)\SelectedSlot
							Players(i)\SelectedSlot = ReadByte(mp_I\Server)
							For j=0 To MaxSlots-1
								If j = Players(i)\SelectedSlot And i = mp_I\PlayerID Then
									prevWeaponInCurrSlot = Players(i)\WeaponInSlot[j]
								EndIf
								Players(i)\WeaponInSlot[j] = ReadByte(mp_I\Server)
								If j = Players(i)\SelectedSlot And i = mp_I\PlayerID Then
									If prevWeaponInCurrSlot <> Players(i)\WeaponInSlot[j] Then
										MouseHit(1)
										g_I\GunChangeFLAG = False
									EndIf
								EndIf
							Next
							If prevSlot<>Players(i)\SelectedSlot And Players(i)\WantsSlot=Players(i)\SelectedSlot Then
								Players(i)\ShootState = 0.0
								Players(i)\ReloadState = 0.0
								Players(i)\DeployState = 0.0
								Players(i)\PressMouse1 = False
								Players(i)\PressReload = False
								If i=mp_I\PlayerID Then
									MouseHit(1)
									g_I\GunChangeFLAG = False
								EndIf
							EndIf
						EndIf
						
						Players(i)\Kills = ReadShort(mp_I\Server)
						
						Local Ping% = ReadInt(mp_I\Server)
						If mp_I\PingTimer <= 0.0 Then
							Players(i)\Ping = Ping
							mp_I\PingTimer = 70.0
						Else
							mp_I\PingTimer = mp_I\PingTimer - FPSfactor
						EndIf
					Else
						If Players(i)<>Null Then
							mp_I\PlayerCount=mp_I\PlayerCount-1
							FreeEntity Players(i)\Collider
							FreeEntity Players(i)\obj_upper
							FreeEntity Players(i)\obj_lower
							FreeEntity Players(i)\NameTag
							Delete Players(i)
							Players(i)=Null
							If mp_I\SpectatePlayer = i Then
								FindPlayerToSpectate(1)
							EndIf
						EndIf
					EndIf
				Next
				
				Local prevEnemyCount = mp_I\Gamemode\EnemyCount
				mp_I\Gamemode\EnemyCount = ReadInt(mp_I\Server)
				Local prevPhase = mp_I\Gamemode\Phase
				mp_I\Gamemode\Phase = ReadByte(mp_I\Server)
				mp_I\Gamemode\PhaseTimer = ReadFloat(mp_I\Server)
				If mp_I\Gamemode\ID = Gamemode_Waves Then
					If prevEnemyCount=0 And prevEnemyCount<>mp_I\Gamemode\EnemyCount Then
						PlaySound_Strict LoadTempSound("SFX\General\WaveStart.ogg")
					EndIf
				ElseIf mp_I\Gamemode\ID = Gamemode_Deathmatch Then
					If prevPhase > Deathmatch_Game And mp_I\Gamemode\Phase = Deathmatch_GameStart Then
						If Players(mp_I\PlayerID)\Team = Team_CI Then
							mp_I\Map\CurrChunk = mp_I\MapInList\CISpawn-(mp_I\MapInList\ChunkStart-1)
						Else
							mp_I\Map\CurrChunk = mp_I\MapInList\NTFSpawn-(mp_I\MapInList\ChunkStart-1)
						EndIf
					EndIf
				EndIf
				
				NoClipSpeed = ReadFloat(mp_I\Server)
				
				getconn = RecvUDPMsg(mp_I\Server)
				;[End Block]
			Case PACKET_ITEM
				;[Block]
				For it = Each Items
					it\noDelete = False
				Next
				temp = ReadInt(mp_I\Server)
				If temp>0
					For i=1 To temp
						itID = ReadInt(mp_I\Server)
						itName = ReadLine(mp_I\Server)
						itTempName = ReadLine(mp_I\Server)
						itX = ReadFloat(mp_I\Server)
						itY = ReadFloat(mp_I\Server)
						itZ = ReadFloat(mp_I\Server)
						itYaw = ReadFloat(mp_I\Server)
						
						found = False
						For it = Each Items
							If it\ID = itID
								found = True
								itFound = it
								Exit
							EndIf
						Next
						
						If found
							PositionEntity itFound\collider,CurveValue(itX,EntityX(itFound\collider),10.0),CurveValue(itY,EntityY(itFound\collider),5.0),CurveValue(itZ,EntityZ(itFound\collider),10.0)
							RotateEntity itFound\collider,0,CurveAngle(itYaw,EntityYaw(itFound\collider),10.0),0
							itFound\noDelete = True
						Else
							it = CreateItem(itName,itTempName,itX,itY,itZ)
							RotateEntity it\collider,0,itYaw,0
							it\ID = itID
							it\noDelete = True
						EndIf
					Next
				EndIf
				For it = Each Items
					If (Not it\noDelete) Then
						PlayItemPickSoundMP(it,-1)
						RemoveItem(it)
					EndIf
				Next
				getconn = RecvUDPMsg(mp_I\Server)
				;[End Block]
			Case PACKET_NPC
				;[Block]
				For n = Each NPCs
					If ShouldSyncNPC(n) Then
						n\noDelete = False
					Else
						n\noDelete = True
					EndIf
				Next
				temp = ReadInt(mp_I\Server)
				If temp>0 Then
					For i=1 To temp
						nID = ReadInt(mp_I\Server)
						ntype = ReadInt(mp_I\Server)
						nX = ReadFloat(mp_I\Server)
						nY = ReadFloat(mp_I\Server)
						nZ = ReadFloat(mp_I\Server)
						;nYaw = ReadFloat(mp_I\Server)
						nState1 = ReadFloat(mp_I\Server)
						nState2 = ReadFloat(mp_I\Server)
						nState3 = ReadFloat(mp_I\Server)
						;nCurrSpeed = ReadFloat(mp_I\Server)
						nHP = ReadInt(mp_I\Server)
						nDistTimer = ReadFloat(mp_I\Server)
						
						found = False
						For n = Each NPCs
							If n\ID = nID Then
								found = True
								nFound = n
								Exit
							EndIf
						Next
						
						If found Then
							If Abs(EntityX(nFound\Collider)-nX) > 0.5 Lor Abs(EntityY(nFound\Collider)-nY) > 0.5 Lor Abs(EntityZ(nFound\Collider)-nZ) > 0.5 Then
								PositionEntity nFound\Collider,nX,nY,nZ
								ResetEntity nFound\Collider
							Else
								PositionEntity nFound\Collider,CurveValue(nX,EntityX(nFound\Collider),10.0),CurveValue(nY,EntityY(nFound\Collider),5.0),CurveValue(nZ,EntityZ(nFound\Collider),10.0)
							EndIf
							;RotateEntity nFound\Collider,0,CurveAngle(nYaw,EntityYaw(nFound\Collider),10.0),0
							nFound\State = nState1
							nFound\State2 = nState2
							nFound\State3 = nState3
							;nFound\CurrSpeed = nCurrSpeed
							nFound\HP = nHP
							nFound\noDelete = True
							nFound\DistanceTimer = nDistTimer
						Else
							n = CreateNPC(ntype,nX,nY,nZ,nState3)
							;RotateEntity n\Collider,0,nYaw,0
							n\ID = nID
							n\State = nState1
							n\State2 = nState2
							n\State3 = nState3
							;n\CurrSpeed = nCurrSpeed
							n\HP = nHP
							n\noDelete = True
							n\DistanceTimer = nDistTimer
						EndIf
					Next
				EndIf
				For n = Each NPCs
					If (Not n\noDelete) Then
						RemoveNPC(n)
					EndIf
				Next
				getconn = RecvUDPMsg(mp_I\Server)
				;[End Block]
			Case PACKET_EFFECT
				;[Block]
				temp = ReadInt(mp_I\Server)
				
				If temp>0 Then
					For i = 1 To temp
						ptype = ReadByte(mp_I\Server)
						
						dx = ReadFloat(mp_I\Server)
						dy = ReadFloat(mp_I\Server)
						dz = ReadFloat(mp_I\Server)
						Select ptype
							Case PARTICLEMP_SHOT
								pmp = CreateParticleMP(ptype,dx,dy,dz,0,0,0,0,0)
							Case PARTICLEMP_WALL
								dpitch = ReadFloat(mp_I\Server)
								dyaw = ReadFloat(mp_I\Server)
								dnx = ReadFloat(mp_I\Server)
								dny = ReadFloat(mp_I\Server)
								dnz = ReadFloat(mp_I\Server)
								pmp = CreateParticleMP(ptype,dx,dy,dz,dpitch,dyaw,dnx,dny,dnz)
							Case PARTICLEMP_035
								pmp = CreateParticleMP(ptype,dx,dy,dz,0,0,0,0,0)
						End Select
					Next
				EndIf
				
				For pmp = Each ParticleMP
					FreeVector3D(pmp\pos)
					FreeVector2D(pmp\rot)
					FreeVector3D(pmp\npos)
					Delete pmp
				Next
				getconn = RecvUDPMsg(mp_I\Server)
				;[End Block]
			Case PACKET_QUIT
				;[Block]
				For i=0 To (mp_I\MaxPlayers-1)
					If Players(i)<>Null Then
						FreeEntity Players(i)\Collider
						FreeEntity Players(i)\obj_upper
						FreeEntity Players(i)\obj_lower
						If i<>mp_I\PlayerID Then
							FreeEntity Players(i)\NameTag
						EndIf
						Delete Players(i)
						Players(i)=Null
					EndIf
				Next
				NullMPGame()
				MainMenuTab = MenuTab_Serverlist
				MainMenuOpen = True
				MenuOpen = False
				SaveMSG = KICK_QUIT
				Return
				;[End Block]
		End Select
	Wend
	IsPlayerSprinting = Players(mp_I\PlayerID)\IsPlayerSprinting
	
	CatchErrors("Uncaught (RecvDataClient)")
End Function

Function SyncServer()
	CatchErrors("SyncServer")
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local g_I.GunInstance = First GunInstance
	Local getconn,i%,j%,k%
	Local it.Items,temp%,n.NPCs
	Local g.Guns,p.Particles,d.Decals
	Local mpl.MainPlayer = First MainPlayer
	Local pmp.ParticleMP
	Local hb.HitBox
	
	Players(mp_I\PlayerID)\Yaw = EntityYaw(mpl\CameraPivot)
	Players(mp_I\PlayerID)\Pitch = EntityPitch(mpl\CameraPivot)
	Players(mp_I\PlayerID)\IronSight = g_I\IronSight
	Players(mp_I\PlayerID)\CurrChunk = mp_I\Map\CurrChunk
	;Sending data to clients
	For i=1 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null
			If Players(i)\Connected Then
				If Players(i)\FinishedLoading Then
					;Package plainly used to determine the ping
					;[Block]
					WriteByte mp_I\Server,PACKET_PING
					Players(i)\LastPingMillisecs = MilliSecs()
					Players(i)\PingCheckValue = Rand(-2147483648,2147483647)
					WriteInt mp_I\Server,Players(i)\PingCheckValue
					SendUDPMsg mp_I\Server,Players(i)\IP,Players(i)\Port
					;[End Block]
					
					;Player data package
					;[Block]
					WriteByte mp_I\Server,PACKET_PLAYER
					For j=0 To (mp_I\MaxPlayers-1)
						If Players(j)<>Null Then
							WriteByte(mp_I\Server,1) ;The user ID is given
							WriteByte(mp_I\Server,Players(j)\Team+1)
							If Players(j)\Team > Team_Spectator Then
								Players(j)\X = EntityX(Players(j)\Collider)
								Players(j)\Y = EntityY(Players(j)\Collider)
								Players(j)\Z = EntityZ(Players(j)\Collider)
								
								;Don't send this data back to the client who already has that data for their own
								If i<>j Then
									WriteLine(mp_I\Server,Players(j)\Name)
									WriteFloat(mp_I\Server,Players(j)\Yaw)
									WriteFloat(mp_I\Server,Players(j)\Pitch)
									WriteFloat(mp_I\Server,Players(j)\CurrSpeed)
									WriteByte mp_I\Server,Int((Players(j)\walkangle/360.0)*255.0)
									WriteFloat(mp_I\Server,Players(j)\ShootState)
									WriteFloat(mp_I\Server,Players(j)\ReloadState)
									WriteFloat(mp_I\Server,Players(j)\DeployState)
									Local byte% = (Players(j)\walking)+(2*Players(j)\IsPlayerSprinting)+(4*Players(j)\Crouch)+(8*Players(j)\IronSight)
									byte = byte + (16*Players(j)\PressMouse1)+(32*Players(j)\PressReload)
									WriteByte mp_I\Server,byte
									WriteByte(mp_I\Server,Players(j)\CurrChunk)
								EndIf
								WriteFloat(mp_I\Server,Players(j)\X)
								WriteFloat(mp_I\Server,Players(j)\Y)
								WriteFloat(mp_I\Server,Players(j)\Z)
								WriteFloat(mp_I\Server,Players(j)\CurrStamina)
								WriteFloat(mp_I\Server,Players(j)\CrouchState)
								WriteFloat(mp_I\Server,Players(j)\CurrHP)
								WriteFloat(mp_I\Server,Players(j)\CurrKevlar)
								
								WriteShort(mp_I\Server,Players(j)\Ammo[SLOT_PRIMARY])
								WriteShort(mp_I\Server,Players(j)\Ammo[SLOT_SECONDARY])
								WriteByte(mp_I\Server,Players(j)\ReloadAmmo[SLOT_PRIMARY])
								WriteByte(mp_I\Server,Players(j)\ReloadAmmo[SLOT_SECONDARY])
								WriteByte(mp_I\Server,Players(j)\SelectedSlot)
								For k=0 To MaxSlots-1
									WriteByte(mp_I\Server,Players(j)\WeaponInSlot[k])
								Next
							EndIf
							
							WriteShort mp_I\Server,Players(j)\Kills
							
							WriteInt mp_I\Server,Players(j)\Ping
						Else
							WriteByte(mp_I\Server,0) ;The user ID is not given
						EndIf
					Next
					WriteInt mp_I\Server,mp_I\Gamemode\EnemyCount
					WriteByte mp_I\Server,mp_I\Gamemode\Phase
					WriteFloat mp_I\Server,mp_I\Gamemode\PhaseTimer
					WriteFloat mp_I\Server,NoClipSpeed ;Will be used for the spectator speed
					SendUDPMsg mp_I\Server,Players(i)\IP,Players(i)\Port
					;[End Block]
					
					;Item syncing (package)
					;[Block]
					WriteByte mp_I\Server,PACKET_ITEM
					temp = 0
					For it.Items = Each Items
						temp=temp+1
					Next
					WriteInt(mp_I\Server,temp)
					For it.Items = Each Items
						WriteInt(mp_I\Server,it\ID)
						WriteLine(mp_I\Server,it\itemtemplate\name)
						WriteLine(mp_I\Server,it\itemtemplate\tempname)
						WriteFloat(mp_I\Server,EntityX(it\collider))
						WriteFloat(mp_I\Server,EntityY(it\collider))
						WriteFloat(mp_I\Server,EntityZ(it\collider))
						WriteFloat(mp_I\Server,EntityYaw(it\collider))
					Next
					SendUDPMsg mp_I\Server,Players(i)\IP,Players(i)\Port
					;[End Block]
					
					;NPC syncing (package)
					;[Block]
					WriteByte mp_I\Server,PACKET_NPC
					temp = 0
					
					For n.NPCs = Each NPCs
						If ShouldSyncNPC(n) Then
							temp=temp+1
						EndIf
					Next
					WriteInt(mp_I\Server,temp)
					For n.NPCs = Each NPCs
						If ShouldSyncNPC(n) Then
							WriteInt(mp_I\Server,n\ID)
							WriteInt(mp_I\Server,n\NPCtype)
							WriteFloat(mp_I\Server,EntityX(n\Collider))
							WriteFloat(mp_I\Server,EntityY(n\Collider))
							WriteFloat(mp_I\Server,EntityZ(n\Collider))
							;WriteFloat(mp_I\Server,EntityYaw(n\Collider))
							WriteFloat(mp_I\Server,n\State)
							WriteFloat(mp_I\Server,n\State2)
							WriteFloat(mp_I\Server,n\State3)
							;WriteFloat(mp_I\Server,n\CurrSpeed)
							WriteInt(mp_I\Server,n\HP)
							WriteFloat(mp_I\Server,n\DistanceTimer)
						EndIf
					Next
					SendUDPMsg mp_I\Server,Players(i)\IP,Players(i)\Port
					;[End Block]
					
					;Effects syncing (package)
					;[Block]
					WriteByte mp_I\Server,PACKET_EFFECT
					For pmp = Each ParticleMP
						temp=temp+1
					Next
					WriteInt(mp_I\Server,temp)
					
					For pmp = Each ParticleMP
						WriteByte mp_I\Server,pmp\ptype
						WriteFloat mp_I\Server,pmp\pos\x
						WriteFloat mp_I\Server,pmp\pos\y
						WriteFloat mp_I\Server,pmp\pos\z
						Select pmp\ptype
							Case PARTICLEMP_WALL
								WriteFloat mp_I\Server,pmp\rot\x
								WriteFloat mp_I\Server,pmp\rot\y
								WriteFloat mp_I\Server,pmp\npos\x
								WriteFloat mp_I\Server,pmp\npos\y
								WriteFloat mp_I\Server,pmp\npos\z
						End Select
					Next
					
					For pmp = Each ParticleMP
						FreeVector3D(pmp\pos)
						FreeVector2D(pmp\rot)
						FreeVector3D(pmp\npos)
						Delete pmp
					Next
					SendUDPMsg mp_I\Server,Players(i)\IP,Players(i)\Port
					;[End Block]
				Else
					;Send a message to the client so they know that they can join
					WriteByte mp_I\Server,1
					SendUDPMsg mp_I\Server,Players(i)\IP,Players(i)\Port
				EndIf
			EndIf
			
			If Players(i)\FinishedLoading Then
				If (MilliSecs()-Players(i)\LastMsgTime>(mp_I\TimeOut*1000)) Then ;remove client after X seconds of inactivity: assume connection was unexpectedly lost
					mp_I\PlayerCount=mp_I\PlayerCount-1
					FreeEntity Players(i)\Collider
					FreeEntity Players(i)\obj_upper
					FreeEntity Players(i)\obj_lower
					FreeEntity Players(i)\NameTag
					Delete Players(i)
					Players(i)=Null
					If mp_I\SpectatePlayer = i Then
						FindPlayerToSpectate(1)
					EndIf
				EndIf
			Else
				Players(i)\LastMsgTime = MilliSecs()
			EndIf
		EndIf
	Next
	
	CatchErrors("Uncaught (SyncServer)")
End Function

Function SyncClient()
	CatchErrors("SyncClient")
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local fo.Fonts = First Fonts
	Local g_I.GunInstance = First GunInstance
	Local getconn,TempRead$,i%,ttmp%,yaw#
	Local mpl.MainPlayer = First MainPlayer
	
	Players(mp_I\PlayerID)\Yaw = EntityYaw(mpl\CameraPivot)
	Players(mp_I\PlayerID)\Pitch = EntityPitch(mpl\CameraPivot)
	Players(mp_I\PlayerID)\IronSight = g_I\IronSight
	
	WriteByte mp_I\Server,PACKET_PLAYER
	WriteByte mp_I\Server,mp_I\PlayerID
	WriteLine mp_I\Server,Players(mp_I\PlayerID)\Name
	WriteFloat mp_I\Server,Players(mp_I\PlayerID)\Yaw
	WriteFloat mp_I\Server,Players(mp_I\PlayerID)\Pitch
	WriteByte mp_I\Server,Int((Players(mp_I\PlayerID)\walkangle/360.0)*255.0)
	WriteByte mp_I\Server,Players(mp_I\PlayerID)\WantsSlot
	
	Local byte% = (Players(mp_I\PlayerID)\walking)+(2*Players(mp_I\PlayerID)\PressSprint)+(4*Players(mp_I\PlayerID)\Crouch)+(8*Players(mp_I\PlayerID)\IronSight)+(16*Players(mp_I\PlayerID)\PressMouse1)
	byte = byte + (32*Min(Players(mp_I\PlayerID)\PressReload+(Players(mp_I\PlayerID)\ReloadState > 0.0),1))
	WriteByte mp_I\Server,byte
	
	WriteByte mp_I\Server,mp_I\Map\CurrChunk
	
	;Some item syncing
	If ClosestItem <> Null
		WriteByte mp_I\Server,1
		WriteInt mp_I\Server,ClosestItem\ID
		WriteByte mp_I\Server,ClosestItem\Picked
	Else
		WriteByte mp_I\Server,0
	EndIf
	
	WriteByte mp_I\Server,Players(mp_I\PlayerID)\Team+1
	
	WriteInt mp_I\Server,Players(mp_I\PlayerID)\PingCheckValue
	
	SendUDPMsg(mp_I\Server,Players(0)\IP,Players(0)\Port)
	
	If Players(0)\FinishedLoading Then
		If (MilliSecs()-Players(0)\LastMsgTime>(mp_I\TimeOut*1000)) Then ;disconnect after X seconds of inactivity: assume connection was unexpectedly lost
			For i=0 To (mp_I\MaxPlayers-1)
				If i<>mp_I\PlayerID Then
					If Players(i)<>Null Then
						FreeEntity Players(i)\Collider
						FreeEntity Players(i)\obj_upper
						FreeEntity Players(i)\obj_lower
						Delete Players(i)
						Players(i)=Null
					EndIf
				EndIf
			Next
			NullMPGame()
			MainMenuTab = MenuTab_Serverlist
			MainMenuOpen = True
			MenuOpen = False
			SaveMSG = KICK_TIMEOUT
			Return
		Else
			;If (MilliSecs()-Players(0)\LastMsgTime)>(100.0) Then
			;	Color 255,255,0
			;	AASetFont fo\ConsoleFont
			;	AAText opt\GraphicWidth,20,"WARNING: Timeout in: "+TurnIntoSeconds((mp_I\TimeOut*1000)-(MilliSecs()-Players(0)\LastMsgTime)),2
			;EndIf
		EndIf
	EndIf
	
	CatchErrors("Uncaught (SyncClient)")
End Function

Function ShouldSyncNPC(n.NPCs)
	
	If n\NPCtype = NPCtypeTentacle And n\Target <> Null Then
		Return False
	EndIf
	
	Return True
	
End Function

Function AnimatePlayerModelsAndSpectate()
	CatchErrors("AnimatePlayerModelsAndSpectate")
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local mpl.MainPlayer = First MainPlayer
	Local g_I.GunInstance = First GunInstance
	
	Local i%,prevFrame#,j%
	Local animspeed_multiply# = 1.0
	Local prevPlayerAnimationLower%, prevPlayerAnimationUpper%
	Local forceUpperRestart%
	Local g.Guns
	
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			animspeed_multiply# = 1.0
			;Will be required for animation transitioning!
			prevPlayerAnimationLower = Players(i)\AnimState_Lower
			prevPlayerAnimationUpper = Players(i)\AnimState_Upper
			forceUpperRestart = False
			
			If Players(i)\CurrHP <= 0 And Players(i)\Team > Team_Spectator Then
				If Players(i)\GunModelMuzzleFlash <> 0 Then
					HideEntity Players(i)\GunModelMuzzleFlash
				EndIf
				Players(i)\AnimState_Lower = STATE_BODY_LOWER_DIE
				For g = Each Guns
					If g\ID = Players(i)\WeaponInSlot[Players(i)\SelectedSlot] Then
						Select g\PlayerModelAnim
							Case GUNANIM_SMG
								Players(i)\AnimState_Upper = STATE_BODY_UPPER_SMG_DIE
							Case GUNANIM_RIFLE
								Players(i)\AnimState_Upper = STATE_BODY_UPPER_RIFLE_DIE
							Case GUNANIM_PISTOL
								Players(i)\AnimState_Upper = STATE_BODY_UPPER_PISTOL_DIE
							Case GUNANIM_MP5K
								Players(i)\AnimState_Upper = STATE_BODY_UPPER_MP5K_DIE
							Case GUNANIM_SHOTGUN
								Players(i)\AnimState_Upper = STATE_BODY_UPPER_SHOTGUN_DIE
							Case GUNANIM_MELEE
								Players(i)\AnimState_Upper = STATE_BODY_UPPER_MELEE_DIE
						End Select
						Exit
					EndIf
				Next
				
				Players(i)\Crouch = 0
				Players(i)\CrouchState = 0.0
				
				If mp_I\DeathChunk = -1 Then
					mp_I\DeathChunk = mp_I\Map\CurrChunk
				EndIf
				
				Players(i)\IronSight = False
				If i = mp_I\PlayerID Then
					If mp_I\Gamemode\ID = Gamemode_Deathmatch Then
						If Players(i)\Team > Team_Spectator Then
							EntityAlpha Players(i)\obj_lower,1.0
							EntityAlpha Players(i)\obj_upper,1.0
						EndIf
					Else
						EntityAlpha Players(i)\obj_lower,1.0
						EntityAlpha Players(i)\obj_upper,1.0
					EndIf
					
					If Players(i)\GunModel<>0 Then
						ShowEntity Players(i)\GunModel
					EndIf
					
					TurnEntity mpl\CameraPivot,0,180,0
					Local pick = EntityPick(mpl\CameraPivot,3.0)
					Local pivot = CreatePivot()
					If PickedEntity() <> 0 Then
						PositionEntity pivot,PickedX(),PickedY(),PickedZ(),True
						PositionEntity Camera,0,0,-Min(Max(EntityDistance(mpl\CameraPivot,pivot)-0.5,0.0),2.0)
					Else
						PositionEntity Camera,0,0,-2.0
					EndIf
					TurnEntity mpl\CameraPivot,0,-180,0
					FreeEntity pivot
					
					HideEntity g_I\GunPivot
					
					g_I\IronSight = False
					
					If mp_I\SpectatePlayer = -1 Then
						mp_I\SpectatePlayer = mp_I\PlayerID
					EndIf
					
					If (Not MenuOpen) And (Not ConsoleOpen) And (Not InTeamSelection()) Then
						If MouseHit1 Then
							FindPlayerToSpectate(1)
						EndIf
						If MouseHit2 Then
							FindPlayerToSpectate(-1)
						EndIf
					EndIf
				EndIf
			Else
				If Players(i)\Team > Team_Spectator Then
					;Upper body animation
					For g = Each Guns
						If g\ID = Players(i)\WeaponInSlot[Players(i)\SelectedSlot] Then
							If Players(i)\GunModelMuzzleFlash<>0 Then
								HideEntity Players(i)\GunModelMuzzleFlash
							EndIf
							If Players(i)\ReloadState > 0.0 Then
								Select g\PlayerModelAnim
									Case GUNANIM_SMG
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_SMG_RELOAD
									Case GUNANIM_RIFLE
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_RIFLE_RELOAD
									Case GUNANIM_PISTOL
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_PISTOL_RELOAD
									Case GUNANIM_MP5K
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_MP5K_RELOAD
									Case GUNANIM_SHOTGUN
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_SHOTGUN_RELOAD
								End Select
								If i<>mp_I\PlayerID Then
									If Players(i)\AnimState_Upper <> prevPlayerAnimationUpper Then
										If g\MaxReloadSounds = 1 Then
											Players(i)\GunSFXChannel[GUN_CHANNEL_OTHER] = PlaySound2(LoadSound_Strict("SFX\Guns\"+g\name$+"\reload.ogg"),Camera,Players(i)\Collider,20)
										Else
											Players(i)\GunSFXChannel[GUN_CHANNEL_OTHER] = PlaySound2(LoadSound_Strict("SFX\Guns\"+g\name$+"\reload"+Rand(1,g\MaxReloadSounds)+".ogg"),Camera,Players(i)\Collider,20)
										EndIf
									EndIf
								EndIf
							ElseIf Players(i)\ShootState = 0.0 Then
								Select g\PlayerModelAnim
									Case GUNANIM_SMG
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_SMG_IDLE
									Case GUNANIM_RIFLE
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_RIFLE_IDLE
									Case GUNANIM_PISTOL
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_PISTOL_IDLE
									Case GUNANIM_MP5K
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_MP5K_IDLE
									Case GUNANIM_SHOTGUN
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_SHOTGUN_IDLE
									Case GUNANIM_MELEE
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_MELEE_IDLE
								End Select
							Else
								Select g\PlayerModelAnim
									Case GUNANIM_SMG
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_SMG_SHOT
									Case GUNANIM_RIFLE
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_RIFLE_SHOT
									Case GUNANIM_PISTOL
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_PISTOL_SHOT
									Case GUNANIM_MP5K
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_MP5K_SHOT
									Case GUNANIM_SHOTGUN
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_SHOTGUN_SHOT
									Case GUNANIM_MELEE
										Players(i)\AnimState_Upper = STATE_BODY_UPPER_MELEE_STAB
								End Select
								If Players(i)\ShootState > 0.0 And (Players(i)\PrevShootState = 0.0 Lor Players(i)\PrevShootState > Players(i)\ShootState) Then
									forceUpperRestart = True
									If i<>mp_I\PlayerID Then
										If Players(i)\PressMouse1 And Players(i)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] > 0 Then
											If Players(i)\ShootState > 0.0 And (Players(i)\PrevShootState = 0.0 Lor Players(i)\PrevShootState > Players(i)\ShootState) Then
												Players(i)\GunSFXChannel[GUN_CHANNEL_SHOT] = PlaySound2(g\ShootSounds[Rand(0,g\MaxShootSounds-1)],Camera,Players(i)\Collider,20)
												If mp_I\PlayState = GAME_CLIENT Then
													Players(i)\PressMouse1 = False
												EndIf
												ShowEntity Players(i)\GunModelMuzzleFlash
												ScaleSprite Players(i)\GunModelMuzzleFlash,Rnd(0.125,0.15),Rnd(0.125,0.15)
												TurnEntity Players(i)\GunModelMuzzleFlash,0,0,Rnd(360)
											EndIf
										EndIf
									EndIf
								EndIf
							EndIf
							For j = 0 To MaxGunChannels-1
								UpdateSoundOrigin(Players(i)\GunSFXChannel[j],Camera,Players(i)\Collider,20)
							Next
							Exit
						EndIf
					Next
					
					;Lower body animation
					If Players(i)\CurrSpeed > 0.0 Then
						Players(i)\walkangle = WrapAngle(Players(i)\walkangle)
						;Maybe some rotation stuff needs to be reworked
						If Players(i)\IsPlayerSprinting Then
							;Temporary
							Players(i)\AnimState_Lower = STATE_BODY_LOWER_RUN
						ElseIf Players(i)\Crouch = False Then
							If Players(i)\walkangle <= 45 Lor Players(i)\walkangle >= 315 Then
								Players(i)\AnimState_Lower = STATE_BODY_LOWER_WALK_FORWARD
							ElseIf Players(i)\walkangle > 45 And Players(i)\walkangle <= 135 Then
								Players(i)\AnimState_Lower = STATE_BODY_LOWER_WALK_LEFT
							Else If Players(i)\walkangle >= 255 And Players(i)\walkangle < 315 Then
								Players(i)\AnimState_Lower = STATE_BODY_LOWER_WALK_RIGHT
							Else
								Players(i)\AnimState_Lower = STATE_BODY_LOWER_WALK_FORWARD
								animspeed_multiply# = -1.0
							EndIf
						Else
							If Players(i)\walkangle <= 45 Lor Players(i)\walkangle >= 315 Then
								Players(i)\AnimState_Lower = STATE_BODY_LOWER_CROUCH_FORWARD
							ElseIf Players(i)\walkangle > 45 And Players(i)\walkangle <= 135 Then
								Players(i)\AnimState_Lower = STATE_BODY_LOWER_CROUCH_LEFT
							Else If Players(i)\walkangle >= 255 And Players(i)\walkangle < 315 Then
								Players(i)\AnimState_Lower = STATE_BODY_LOWER_CROUCH_RIGHT
							Else
								Players(i)\AnimState_Lower = STATE_BODY_LOWER_CROUCH_FORWARD
								animspeed_multiply# = -1.0
							EndIf
						EndIf
						
						;Stepsounds will come into the animation function itself
;						If mp_I\PlayerID<>i Then
;							If prevFrame < 506 And AnimTime(Players(i)\obj_lower) >= 506 Then
;								PlaySound2(StepSFX(GetStepSound(Players(i)\Collider),0,Rand(0,7)),Camera,Players(i)\Collider,5,1.0)
;							ElseIf prevFrame => 521 And AnimTime(Players(i)\obj_lower) < 489 Then
;								PlaySound2(StepSFX(GetStepSound(Players(i)\Collider),0,Rand(0,7)),Camera,Players(i)\Collider,5,1.0)
;							EndIf
;						EndIf
					Else
						If Players(i)\Crouch = False Then
							Players(i)\AnimState_Lower = STATE_BODY_LOWER_IDLE
						Else
							Players(i)\AnimState_Lower = STATE_BODY_LOWER_CROUCH_IDLE
						EndIf
					EndIf
				EndIf
				
				If i = mp_I\PlayerID Then
					If Players(i)\Team > Team_Spectator Then
						EntityAlpha Players(i)\obj_lower,0.0
						EntityAlpha Players(i)\obj_upper,0.0
					EndIf
					PositionEntity Camera,0,0,0
					mp_I\SpectatePlayer = -1
					If Players(i)\GunModel <> 0 Then
						HideEntity Players(i)\GunModel
					EndIf
				EndIf
			EndIf
			
			If Players(i)\Team > Team_Spectator Then
				If Players(i)\AnimState_Lower <> prevPlayerAnimationLower Then
					AnimatePlayerModelLow(Players(i)\obj_lower, Players(i)\AnimState_Lower, animspeed_multiply)
				EndIf
				
				If Players(i)\AnimState_Upper <> prevPlayerAnimationUpper Lor forceUpperRestart Then
					AnimatePlayerModelUp(Players(i)\obj_upper, Players(i)\AnimState_Upper)
				EndIf
				
				If i <> mp_I\PlayerID Then
					PlayPlayerStepSounds(i, Players(i)\obj_lower, Players(i)\AnimState_Lower)
				EndIf
			EndIf
		EndIf
	Next
	
	CatchErrors("Uncaught (AnimatePlayerModelsAndSpectate)")
End Function

Function FindPlayerToSpectate(iterator%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Repeat
		mp_I\SpectatePlayer = mp_I\SpectatePlayer + iterator
		If mp_I\SpectatePlayer > (mp_I\MaxPlayers-1) Then
			mp_I\SpectatePlayer = 0
		ElseIf mp_I\SpectatePlayer < 0 Then
			mp_I\SpectatePlayer = mp_I\MaxPlayers-1
		EndIf
		
		If Players(mp_I\SpectatePlayer) <> Null Then
			If (Players(mp_I\SpectatePlayer)\Team = Players(mp_I\PlayerID)\Team) Lor (Players(mp_I\PlayerID)\Team <= Team_Spectator) Then
				mp_I\Map\CurrChunk = Players(mp_I\SpectatePlayer)\CurrChunk
				Return
			EndIf
		EndIf
	Forever
	
End Function

Function ManipulatePlayerModelBones()
	CatchErrors("ManipulatePlayerModelBones")
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local i%,bonename$,bone%
	
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			If mp_I\PlayerID<>i Then
				If Players(i)\CurrHP > 0 And Players(i)\Team > Team_Spectator Then
					bonename$ = "chest"
					If bonename <> "" Then
						bone% = FindChild(Players(i)\obj_upper,bonename$)
						If bone <> 0
							RotateEntity bone%,EntityPitch(bone),-Players(i)\Pitch,EntityRoll(bone)
						EndIf
					EndIf
				EndIf
			EndIf
		EndIf
	Next
	
	CatchErrors("Uncaught (ManipulatePlayerModelBones)")
End Function

Function DrawMPGunsInHud()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local fo.Fonts = First Fonts
	Local g_I.GunInstance = First GunInstance
	Local g.Guns
	Local x# = 50, x2# = 150
	Local y# = 50
	Local width#=64,height#=64
	
	Local width2%
	Local i%
	width# = 204
	height# = 20
	
	x# = opt\GraphicWidth - 60
	y# = opt\GraphicHeight - 55
	
	For g = Each Guns
		If g\ID = Players(mp_I\PlayerID)\WeaponInSlot[Players(mp_I\PlayerID)\SelectedSlot] Then
			If (g\GunType <> GUNTYPE_MELEE) Then
				If Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] > 0 Then
					Color 255,255,255
				Else
					Color 255,0,0
				EndIf
				Rect(x - 50 - 1 - 30, y - 1, 30 + 2, 30 + 2, False)
				DrawImage BulletIcon, x - 50 - 30, y
				
				AASetFont fo\Font%[Font_Digital_Medium]
				If Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] > g\MaxCurrAmmo/5
					Color 0,255,0
				Else
					Color 255,0,0
				EndIf
				AAText x,y-5,Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot],2
				Color 0,255,0
				AAText x,y-5,"/"
				width2% = AAStringWidth("/")
				If Players(mp_I\PlayerID)\ReloadAmmo[Players(mp_I\PlayerID)\SelectedSlot] > 0
					Color 0,255,0
				Else
					Color 255,0,0
				EndIf
				AAText x+width2,y-5,Players(mp_I\PlayerID)\ReloadAmmo[Players(mp_I\PlayerID)\SelectedSlot]
			EndIf
			Exit
		EndIf
	Next
	
	Color 255,255,255
	
End Function

Function InitMPItemTemplates()
	Local it.ItemTemplates,it2.ItemTemplates
	
	it = CreateItemTemplate("Ballistic Vest", "vest", "GFX\items\vest.b3d", "GFX\items\INVvest.jpg", "", 0.02,"GFX\items\Vest.png")
	it\sound = 2
	;it\fastPickable = True
	it = CreateItemTemplate("Heavy Ballistic Vest", "finevest", "GFX\items\vest.b3d", "GFX\items\INVvest.jpg", "", 0.022,"GFX\items\Vest.png")
	it\sound = 2
	;it\fastPickable = True
	
	it = CreateItemTemplate("First Aid Kit", "firstaid", "GFX\items\firstaid.x", "GFX\items\INVfirstaid.jpg", "", 0.05)
	it = CreateItemTemplate("Small First Aid Kit", "finefirstaid", "GFX\items\firstaid.x", "GFX\items\INVfirstaid.jpg", "", 0.03)
	it = CreateItemTemplate("Blue First Aid Kit", "firstaid2", "GFX\items\firstaid.x", "GFX\items\INVfirstaid2.jpg", "", 0.03, "GFX\items\firstaidkit2.jpg")
	
	it = CreateItemTemplate("Syringe", "syringe", "GFX\items\Syringe\syringe.b3d", "GFX\items\Syringe\inv.png", "", 0.005) : it\sound = 2
	
;	it = CreateItemTemplate("USP Tactical", "usp", "GFX\weapons\USP_Tactical_Worldmodel.b3d", "GFX\weapons\INVusp.jpg", "", 0.02)
;	it\isGun% = True
;	it\sound = 66
;	it = CreateItemTemplate("Silenced USP Tactical", "usp", "GFX\weapons\USP_Tactical_Worldmodel_Silenced.b3d", "GFX\weapons\INVusp_silenced.jpg", "", 0.02)
;	it\isGun% = True
;	it\sound = 66
;	it = CreateItemTemplate("FN P90", "p90", "GFX\weapons\P90_Worldmodel.b3d", "GFX\weapons\INVp90.jpg", "", 0.02)
;	it\isGun% = True
;	it\sound = 66
;	it = CreateItemTemplate("MP5K", "mp5k", "GFX\weapons\MP5K_Worldmodel.b3d", "NineTailedFoxMod\GFX\items\INVfreezer.jpg", "", 0.003)
;	it\isGun% = True
;	it\sound = 66
;	it = CreateItemTemplate("Crowbar", "crowbar", "NineTailedFoxMod\GFX\items\Crowbar_Worldmodel.b3d", "NineTailedFoxMod\GFX\items\INVfreezer.jpg", "", 0.03)
;	it\isGun% = True
;	it\sound = 66
;	it = CreateItemTemplate("M9 Beretta", "m9", "NineTailedFoxMod\GFX\items\M9_Beretta_Worldmodel.b3d", "NineTailedFoxMod\GFX\items\INVfreezer.jpg", "", 0.02)
;	it\isGun% = True
;	it\sound = 66
	CreateItemTemplate("Silencer", "silencer", "GFX\weapons\Silencer.b3d","GFX\weapons\INVsilencer.jpg","",0.02)
	CreateItemTemplate("Ammo Crate", "ammocrate", "GFX\map\Props\crate2.x", "NineTailedFoxMod\GFX\items\INVfreezer.jpg", "", 0.01)
	
	For it = Each ItemTemplates
		If (it\tex<>0) Then
			If (it\texpath<>"") Then
				For it2=Each ItemTemplates
					If (it2<>it) And (it2\tex=it\tex) Then
						it2\tex = 0
					EndIf
				Next
			EndIf
			DeleteSingleTextureEntryFromCache it\tex : it\tex = 0
		EndIf
	Next
	
End Function

Type ItemSpawner
	Field obj%
	Field itemtype%
	Field time#
	Field picked%
	Field item.Items
	Field respawntime%
	Field rndtime1%,rndtime2%
End Type

Function CreateItemSpawner.ItemSpawner(x#,y#,z#,itemtype%,rstime%,rndtime1%=0,rndtime2%=0)
	Local its.ItemSpawner = New ItemSpawner
	
	its\obj = CreatePivot()
	PositionEntity its\obj,x#,y#,z#
	its\itemtype = itemtype
	its\picked = True
	its\respawntime = rstime
	its\rndtime1 = rndtime1
	its\rndtime2 = rndtime2
	
	Return its
End Function

Function UpdateItemSpawners()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local its.ItemSpawner,Random%,GunID%,MaxReloadAmmo%
	Local g.Guns,gunAmount%
	
	Local stopItemSpawn% = False
	If mp_I\Gamemode\ID = Gamemode_Waves Then
		If mp_I\Gamemode\Phase > 0 And (mp_I\Gamemode\Phase Mod 2) = 0 Then
			stopItemSpawn = True
		EndIf
	EndIf
	
	If (Not stopItemSpawn) Then
		For its = Each ItemSpawner
			If its\time <= 0.0 Then
				If its\picked Then
					Select its\itemtype
						Case ITEMTYPE_GUN
							gunAmount = 0
							For g = Each Guns
								gunAmount = gunAmount + 1
							Next
							Random = Rand(1,gunAmount)
							For g = Each Guns
								If g\ID = Random Then
									its\item = CreateItem(g\DisplayName, g\name, EntityX(its\obj),EntityY(its\obj),EntityZ(its\obj))
									GunID = g\ID
									MaxReloadAmmo = g\MaxReloadAmmo
								EndIf
							Next
							its\item\state = GetWeaponMaxCurrAmmo(GunID)
							its\item\state2 = Floor(MaxReloadAmmo/2.0)
							EntityType its\item\collider,HIT_ITEM
						Case ITEMTYPE_HEALTH
							Random = Rand(0,3)
							Select Random
								Case 0
									its\item = CreateItem("First Aid Kit","firstaid",EntityX(its\obj),EntityY(its\obj),EntityZ(its\obj))
								Case 1
									its\item = CreateItem("Small First Aid Kit","finefirstaid",EntityX(its\obj),EntityY(its\obj),EntityZ(its\obj))
								Case 2
									its\item = CreateItem("Blue First Aid Kit","firstaid2",EntityX(its\obj),EntityY(its\obj),EntityZ(its\obj))
								Case 3
									its\item = CreateItem("Syringe","syringe",EntityX(its\obj),EntityY(its\obj),EntityZ(its\obj))
							End Select
							EntityType its\item\collider,HIT_ITEM
						Case ITEMTYPE_KEVLAR
							Random = Rand(0,1)
							Select Random
								Case 0
									its\item = CreateItem("Ballistic Vest","vest",EntityX(its\obj),EntityY(its\obj),EntityZ(its\obj))
								Case 1
									its\item = CreateItem("Heavy Ballistic Vest","finevest",EntityX(its\obj),EntityY(its\obj),EntityZ(its\obj))
							End Select
							EntityType its\item\collider,HIT_ITEM
						Case ITEMTYPE_AMMO
							its\item = CreateItem("Ammo Crate","ammocrate",EntityX(its\obj),EntityY(its\obj),EntityZ(its\obj))
							EntityType its\item\collider,HIT_ITEM
					End Select
				EndIf
				its\picked = False
				If its\item = Null
					its\picked = True
					If mp_I\Gamemode\ID = Gamemode_Waves Then
						its\time = mp_I\Gamemode\PhaseTimer+70
					Else
						its\time = 70*(its\respawntime+Rand(its\rndtime1,its\rndtime2))
					EndIf
				EndIf
			Else
				its\time = its\time - FPSfactor
			EndIf
		Next
	EndIf
	
End Function

Function UpdateMPItems()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local n, i.Items
	Local xtemp#, ytemp#, ztemp#
	Local temp%, np.NPCs
	
	Local HideDist = HideDistance*0.5
	Local deletedItem% = False
	
	ClosestItem = Null
	For i.Items = Each Items
		If i\collider<>0 Then
			i\Dropped = 0
			
			If (Not i\Picked) Then
				If i\disttimer < MilliSecs() Then
					i\dist = EntityDistance(Players(mp_I\PlayerID)\Collider, i\collider)
					i\disttimer = MilliSecs() + 700
					If i\dist < HideDist Then ShowEntity i\model
				EndIf
				
				If i\dist < HideDist Then
					ShowEntity i\model
					
					If i\dist < 1.2 Then
						If ClosestItem = Null Then
							If EntityInView(i\model, Camera) Then
								If EntityVisible(i\collider,Camera) Then
									ClosestItem = i
								EndIf
							EndIf
						ElseIf ClosestItem = i Lor i\dist < EntityDistance(Camera, ClosestItem\collider) Then ;TODO check item\distsquared?
							If EntityInView(i\model, Camera) Then
								If EntityVisible(i\collider,Camera) Then
									ClosestItem = i
								EndIf
							EndIf
						EndIf
					EndIf
				Else
					HideEntity i\model
				EndIf
			EndIf
			deletedItem = False
		EndIf
	Next
	
	If MenuOpen Lor ConsoleOpen Then ClosestItem=Null
	If Players(mp_I\PlayerID)\CurrHP <= 0 Then ClosestItem = Null
	
	If ClosestItem <> Null Then
		If keyhituse Then
			PickMPItem(ClosestItem,mp_I\PlayerID)
		EndIf
	EndIf
	
End Function

Function UpdateMPItemsGravity()
	Local i.Items,i2.Items
	Local xtemp#,ytemp#,ztemp#,ed#
	
	For i.Items = Each Items
		If i\collider<>0 Then
			If EntityCollided(i\collider, HIT_MAP) Then
				i\DropSpeed = 0
				i\xspeed = 0.0
				i\zspeed = 0.0
			Else
;				If ShouldEntitiesFall Then
;					Local pick = LinePick(EntityX(i\collider),EntityY(i\collider),EntityZ(i\collider),0,-10,0)
;					If pick
;						i\DropSpeed = i\DropSpeed - 0.0004 * FPSfactor
;						TranslateEntity i\collider, i\xspeed*FPSfactor, i\DropSpeed * FPSfactor, i\zspeed*FPSfactor
;						If i\WontColl Then ResetEntity(i\collider)
;					Else
;						i\DropSpeed = 0
;						i\xspeed = 0.0
;						i\zspeed = 0.0
;					EndIf
;				Else
;					i\DropSpeed = 0
;					i\xspeed = 0.0
;					i\zspeed = 0.0
;				EndIf
				i\DropSpeed = i\DropSpeed - 0.0004 * FPSfactor
				TranslateEntity i\collider, i\xspeed*FPSfactor, i\DropSpeed * FPSfactor, i\zspeed*FPSfactor
				If i\WontColl Then ResetEntity(i\collider)
			EndIf
			
			For i2.Items = Each Items
				If i<>i2 And (Not i2\Picked) Then
					
					xtemp# = (EntityX(i2\collider,True)-EntityX(i\collider,True))
					ytemp# = (EntityY(i2\collider,True)-EntityY(i\collider,True))
					ztemp# = (EntityZ(i2\collider,True)-EntityZ(i\collider,True))
					
					ed# = (xtemp*xtemp+ztemp*ztemp)
					If ed<0.07 And Abs(ytemp)<0.25 Then
						;items are too close together, push away
						
						xtemp = xtemp*(0.07-ed)
						ztemp = ztemp*(0.07-ed)
						
						While Abs(xtemp)+Abs(ztemp)<0.001
							xtemp = xtemp+Rnd(-0.002,0.002)
							ztemp = ztemp+Rnd(-0.002,0.002)
						Wend
						
						TranslateEntity i2\collider,xtemp,0,ztemp
						TranslateEntity i\collider,-xtemp,0,-ztemp
					EndIf
				EndIf
			Next
			
			If EntityY(i\collider) < - 35.0 Then DebugLog "remove: " + i\itemtemplate\name : RemoveItem(i)
		EndIf
	Next
	
End Function

Function PickMPItem(item.Items,playerID%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local g_I.GunInstance = First GunInstance
	Local mpl.MainPlayer = First MainPlayer
	CatchErrors("Uncaught (PickMPItem)")
	Local picked = False
	Local i%
	Local g.Guns,g2.Guns
	Local it2.Items
	
	Select item\itemtemplate\tempname
		Case "vest"
			If Players(playerID)\CurrKevlar < 100 Then
				PlayItemPickSoundMP(item,playerID)
				If mp_I\PlayState=GAME_SERVER Then
					Players(playerID)\CurrKevlar = 100
				EndIf
				picked = True
			EndIf
		Case "finevest"
			If Players(playerID)\CurrKevlar < 100 Then
				PlayItemPickSoundMP(item,playerID)
				If mp_I\PlayState=GAME_SERVER Then
					Players(playerID)\CurrKevlar = 100
				EndIf
				picked = True
			EndIf
		Case "firstaid","firstaid2","finefirstaid"
			If Players(playerID)\CurrHP < 100 Then
				PlayItemPickSoundMP(item,playerID)
				If mp_I\PlayState=GAME_SERVER Then
					Select item\itemtemplate\tempname
						Case "firstaid"
							Players(playerID)\CurrHP = Min(Players(playerID)\CurrHP+75,100)
						Case "firstaid2"
							Players(playerID)\CurrHP = Min(Players(playerID)\CurrHP+50,100)
						Case "finefirstaid"
							Players(playerID)\CurrHP = Min(Players(playerID)\CurrHP+25,100)
					End Select		
				EndIf
				picked = True
			EndIf
		Case "syringe"
			If Players(playerID)\CurrStamina < 100 Lor Players(playerID)\CurrHP < 100 Then
				PlayItemPickSoundMP(item,playerID)
				If mp_I\PlayState=GAME_SERVER Then
					Players(playerID)\CurrStamina = 100
					Players(playerID)\CurrHP = Min(Players(playerID)\CurrHP+10,100)
				EndIf
				picked = True
			EndIf
		Case "ammocrate"
			For i = 0 To MaxSlots-2
				For g = Each Guns
					If g\ID = Players(playerID)\WeaponInSlot[i] Then
						If Players(playerID)\ReloadAmmo[i]<g\MaxReloadAmmo Then
							PlayItemPickSoundMP(item,playerID)
							If mp_I\PlayState=GAME_SERVER Then
								If g\GunType = GUNTYPE_SHOTGUN Then
									Players(playerID)\ReloadAmmo[i]=Min(Players(playerID)\ReloadAmmo[i]+(2*g\MaxCurrAmmo),g\MaxReloadAmmo)
								Else
									Players(playerID)\ReloadAmmo[i]=Min(Players(playerID)\ReloadAmmo[i]+2,g\MaxReloadAmmo)
								EndIf
							EndIf
							picked = True
							Exit
						EndIf
					EndIf
				Next
			Next
		Default
			If item\itemtemplate\isGun Then
				Local found = False
				For i = 0 To MaxSlots-1
					For g = Each Guns
						If g\name = item\itemtemplate\tempname Then
							If g\ID = Players(playerID)\WeaponInSlot[i] Then
								found = True
								Exit
							EndIf
						EndIf
					Next
					If found Then
						Exit
					EndIf
				Next
				For g = Each Guns
					If g\name = item\itemtemplate\tempname Then
						If (Not found) Then
							;Not found, pick up the weapon
							PlayItemPickSoundMP(item,playerID)
							If playerID=mp_I\PlayerID Then
								mpl\SlotsDisplayTimer = 70*3
							EndIf
							If mp_I\PlayState=GAME_SERVER Then
								For g2 = Each Guns
									If g2\ID = Players(playerID)\WeaponInSlot[g\Slot] Then
										it2 = CreateItem(g2\DisplayName,g2\name,EntityX(Players(playerID)\Collider),EntityY(Players(playerID)\Collider),EntityZ(Players(playerID)\Collider))
										EntityType it2\collider,HIT_ITEM
										it2\xspeed = Cos(Players(playerID)\Yaw+90)*0.005
										it2\zspeed = Sin(Players(playerID)\Yaw+90)*0.005
										it2\state = Players(playerID)\Ammo[g\Slot]
										it2\state2 = Players(playerID)\ReloadAmmo[g\Slot]
									EndIf
								Next
								Players(playerID)\WeaponInSlot[g\Slot] = g\ID
								If g\GunType <> GUNTYPE_MELEE Then
									Players(playerID)\Ammo[g\Slot] = item\state
									Players(playerID)\ReloadAmmo[g\Slot] = item\state2
								EndIf
								If playerID = mp_I\PlayerID Then
									g_I\GunChangeFLAG = False
									Players(playerID)\SelectedSlot = g\Slot
								Else
									Players(playerID)\DeployState = 0
									Players(playerID)\ReloadState = 0
									Players(playerID)\ShootState = 0
									Players(playerID)\PressMouse1 = False
									Players(playerID)\PressReload = False
								EndIf
							Else
								Players(playerID)\WantsSlot = g\Slot
							EndIf
							picked = True
						Else
							;Found, pick up weapon as 1 magazine if possible
							For i = 0 To MaxSlots-2
								If g\ID = Players(playerID)\WeaponInSlot[i] Then
									If Players(playerID)\ReloadAmmo[i]<g\MaxReloadAmmo Then
										PlayItemPickSoundMP(item,playerID)
										If mp_I\PlayState=GAME_SERVER Then
											If g\GunType = GUNTYPE_SHOTGUN Then
												Players(playerID)\ReloadAmmo[i]=Min(Players(playerID)\ReloadAmmo[i]+(1*g\MaxCurrAmmo),g\MaxReloadAmmo)
											Else
												Players(playerID)\ReloadAmmo[i]=Min(Players(playerID)\ReloadAmmo[i]+1,g\MaxReloadAmmo)
											EndIf
										EndIf
										picked = True
									EndIf
									Exit
								EndIf
							Next
						EndIf
						Exit
					EndIf
				Next
			EndIf
	End Select
	
	If picked Then
		If mp_I\PlayState=GAME_SERVER Then
			RemoveItem(item)
		Else
			item\Picked = True
		EndIf
	EndIf
	
	CatchErrors("PickMPItem")
End Function

Function PlayItemPickSoundMP(item.Items,playerID%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local g.Guns
	
	Select item\itemtemplate\tempname
		Case "vest","finevest"
			If playerID=mp_I\PlayerID Then
				PlaySound_Strict LoadTempSound("NineTailedFoxMod\SFX\Interact\PickUpKevlar.ogg")
			Else
				PlaySound2(LoadTempSound("NineTailedFoxMod\SFX\Interact\PickUpKevlar.ogg"),Camera,item\collider,5)
			EndIf
		Default
			If item\itemtemplate\isGun Then
				For g = Each Guns
					If g\name = item\itemtemplate\tempname Then
						If playerID=mp_I\PlayerID Then
							PlaySound_Strict LoadTempSound("SFX\Guns\"+g\name+"\pickup.ogg")
						Else
							PlaySound2(LoadTempSound("SFX\Guns\"+g\name+"\pickup.ogg"),Camera,item\collider,5)
						EndIf
						Exit
					EndIf
				Next
			Else
				If playerID=mp_I\PlayerID Then
					PlaySound_Strict PickSFX(item\itemtemplate\sound)
				Else
					PlaySound2(PickSFX(item\itemtemplate\sound),Camera,item\collider,5)
				EndIf
			EndIf
	End Select
	
End Function

;Unused
;[Block]
;Function RemoveMPItem(i.Items)
;	CatchErrors("Uncaught (RemoveMPItem)")
;	FreeEntity(i\model) : FreeEntity(i\collider) : i\collider = 0
;	
;	If i\itemtemplate\img <> 0
;		FreeImage i\itemtemplate\img
;		i\itemtemplate\img = 0
;	EndIf
;	
;	Delete i
;	
;	CatchErrors("RemoveMPItem")
;End Function
;[End Block]

Function DrawMPMenu()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local fo.Fonts = First Fonts
	Local co.Controller = First Controller
	CatchErrors("Uncaught (DrawMenu)")
	
	Local x%, y%, width%, height%
	If (Not InFocus()) Then ;Game is out of focus -> pause the game
        MenuOpen = True
        ;PauseSounds()
        ;Delay 1000 ;Reduce the CPU take while game is not in focus
    EndIf
	
	Local i%
	
;	If KeyDown(15) Then
;		
;	EndIf
	
	If MenuOpen Then
		
		width = ImageWidth(PauseMenuIMG)
		height = ImageHeight(PauseMenuIMG)
		x = opt\GraphicWidth / 2 - width / 2
		y = opt\GraphicHeight / 2 - height / 2
		
		DrawImage PauseMenuIMG, x, y
		
		Color(255, 255, 255)
		
		x = x+132*MenuScale
		y = y+122*MenuScale	
		
		If AchievementsMenu > 0 Then
			AASetFont fo\Font[Font_Menu]
			AAText(x, y-(122-45)*MenuScale, "ACHIEVEMENTS",False,True)
			AASetFont fo\Font[Font_Default]
		ElseIf OptionsMenu > 0 Then
			AASetFont fo\Font[Font_Menu]
			AAText(x, y-(122-45)*MenuScale, "OPTIONS",False,True)
			AASetFont fo\Font[Font_Default]
		ElseIf QuitMSG > 0 Then
			AASetFont fo\Font[Font_Menu]
			AAText(x, y-(122-45)*MenuScale, "ARE YOU SURE?",False,True)
			AASetFont fo\Font[Font_Default]
		ElseIf KillTimer >= 0 Then
			AASetFont fo\Font[Font_Menu]
			AAText(x, y-(122-45)*MenuScale, "PAUSED",False,True)
			AASetFont fo\Font[Font_Default]
		Else
			AASetFont fo\Font[Font_Menu]
			AAText(x, y-(122-45)*MenuScale, "YOU DIED",False,True)
			AASetFont fo\Font[Font_Default]
		End If		
		
		Local AchvXIMG% = (x + (22*MenuScale))
		Local scale# = opt\GraphicHeight/768.0
		Local SeparationConst% = 76*scale
		Local imgsize% = 64
		
		If AchievementsMenu <= 0 And OptionsMenu <= 0 And QuitMSG <= 0
			AASetFont fo\Font[Font_Default]
			AAText x, y, "Username: "+mp_I\PlayerName
			;AAText x, y+20*MenuScale, "Server IP: "+DottedIP(Players(0)\IP)+":"+Players(0)\Port
			;AAText x, y+40*MenuScale, "Server Name: "+
		ElseIf AchievementsMenu <= 0 And OptionsMenu > 0 And QuitMSG <= 0 And KillTimer >= 0
			Color 0,255,0
			If OptionsMenu = 1
				Rect(x-10*MenuScale,y-5*MenuScale,110*MenuScale,40*MenuScale,True)
			ElseIf OptionsMenu = 2
				Rect(x+100*MenuScale,y-5*MenuScale,110*MenuScale,40*MenuScale,True)
			ElseIf OptionsMenu = 3
				Rect(x+210*MenuScale,y-5*MenuScale,110*MenuScale,40*MenuScale,True)
			ElseIf OptionsMenu = 4
				Rect(x+320*MenuScale,y-5*MenuScale,110*MenuScale,40*MenuScale,True)
			EndIf
			
			Local tx# = (opt\GraphicWidth/2)+(width/2)
			Local ty# = y
			Local tw# = 400*MenuScale
			Local th# = 150*MenuScale
			
			Color 255,255,255
			Select OptionsMenu
				Case 1 ;Graphics
					;[Block]
					AASetFont fo\Font[Font_Default]
					
					y=y+50*MenuScale
					
					Color 255,255,255
					AAText(x, y, "VSync:")
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale) And OnSliderID=0
						DrawOptionsTooltip(tx,ty,tw,th,"vsync")
					EndIf
					
					y=y+30*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Anti-aliasing:")
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale) And OnSliderID=0
						DrawOptionsTooltip(tx,ty,tw,th,"antialias")
					EndIf
					
					y=y+30*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Enable room lights:")
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale) And OnSliderID=0
						DrawOptionsTooltip(tx,ty,tw,th,"roomlights")
					EndIf
					
					y=y+30*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Screen gamma")
					If MouseOn(x+270*MenuScale,y+6*MenuScale,100*MenuScale+14,20) And OnSliderID=0
						DrawOptionsTooltip(tx,ty,tw,th,"gamma")
					EndIf
					
					y=y+50*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Particle amount:")
					If (MouseOn(x + 270 * MenuScale, y-6*MenuScale, 100*MenuScale+14, 20) And OnSliderID=0) Lor OnSliderID=2
						DrawOptionsTooltip(tx,ty,tw,th,"particleamount",ParticleAmount)
					EndIf
					
					y=y+50*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Texture LOD Bias:")
					If (MouseOn(x+270*MenuScale,y-6*MenuScale,100*MenuScale+14,20) And OnSliderID=0) Lor OnSliderID=3
						DrawOptionsTooltip(tx,ty,tw,th+100*MenuScale,"texquality")
					EndIf
					
					y=y+50*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Cubemap render mode:")
					If (MouseAndControllerSelectBox(x + 270 * MenuScale, y-6*MenuScale, 100*MenuScale+14, 20, 8, MainMenuTab) And OnSliderID=0) Lor OnSliderID=4
						DrawOptionsTooltip(tx,ty,tw,th+100*MenuScale,"cubemap",RenderCubeMapMode)
					EndIf
					
					y=y+50*MenuScale
					Color 255,255,255
					AAText(x, y, "Field of view:")
					Color 255,255,0
					AAText(x + 5 * MenuScale, y + 25 * MenuScale, Int(FOV#)+" FOV")
					If MouseOn(x+250*MenuScale,y-4*MenuScale,100*MenuScale+14,20)
						DrawOptionsTooltip(tx,ty,tw,th,"fov")
					EndIf
					;[End Block]
				Case 2 ;Audio
					;[Block]
					AASetFont fo\Font[Font_Default]
					
					y = y + 50*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Music volume:")
					If MouseOn(x+250*MenuScale,y-4*MenuScale,100*MenuScale+14,20)
						DrawOptionsTooltip(tx,ty,tw,th,"musicvol")
					EndIf
					
					y = y + 30*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Sound volume:")
					If MouseOn(x+250*MenuScale,y-4*MenuScale,100*MenuScale+14,20)
						DrawOptionsTooltip(tx,ty,tw,th,"soundvol")
					EndIf
					
					y = y + 30*MenuScale
					
					Color 100,100,100
					AAText x, y, "Sound auto-release:"
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale)
						DrawOptionsTooltip(tx,ty,tw,th+220*MenuScale,"sfxautorelease")
					EndIf
					
					y = y + 30*MenuScale
					
					Color 100,100,100
					AAText x, y, "Enable user tracks:"
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale)
						DrawOptionsTooltip(tx,ty,tw,th,"usertrack")
					EndIf
					
					If EnableUserTracks
						y = y + 30 * MenuScale
						Color 255,255,255
						AAText x, y, "User track mode:"
						If UserTrackMode
							AAText x, y + 20 * MenuScale, "Repeat"
						Else
							AAText x, y + 20 * MenuScale, "Random"
						EndIf
						If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale)
							DrawOptionsTooltip(tx,ty,tw,th,"usertrackmode")
						EndIf
					EndIf
					;[End Block]
				Case 3 ;Controls
					;[Block]
					AASetFont fo\Font[Font_Default]
					
					y = y + 50*MenuScale
					
					Color(255, 255, 255)
					AAText(x, y, "Mouse sensitivity:")
					If MouseOn(x+270*MenuScale,y-4*MenuScale,100*MenuScale,20)
						DrawOptionsTooltip(tx,ty,tw,th,"mousesensitivity")
					EndIf
					
					y = y + 30*MenuScale
					
					Color(255, 255, 255)
					AAText(x, y, "Invert mouse Y-axis:")
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale)
						DrawOptionsTooltip(tx,ty,tw,th,"mouseinvert")
					EndIf
					
					y = y + 30*MenuScale
					AAText(x, y, "Control configuration:")
					y = y + 10*MenuScale
					
					AAText(x, y + 20 * MenuScale, "Move Forward")
					
					AAText(x, y + 40 * MenuScale, "Strafe Left")
					
					AAText(x, y + 60 * MenuScale, "Move Backward")
					
					AAText(x, y + 80 * MenuScale, "Strafe Right")
					
					
					AAText(x, y + 100 * MenuScale, "Manual Blink")
					
					AAText(x, y + 120 * MenuScale, "Sprint")
					
					AAText(x, y + 140 * MenuScale, "Open/Close Inventory")
					
					AAText(x, y + 160 * MenuScale, "Crouch")
					
					AAText(x, y + 180 * MenuScale, "Quick Save")
					
					AAText(x, y + 200 * MenuScale, "Open/Close Console")
					
					If MouseOn(x,y,300*MenuScale,220*MenuScale)
						DrawOptionsTooltip(tx,ty,tw,th,"controls")
					EndIf
					;[End Block]
				Case 4 ;Advanced
					;[Block]
					AASetFont fo\Font[Font_Default]
					
					y = y + 50*MenuScale
					
					Color 255,255,255				
					AAText(x, y, "Show HUD:")
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale)
						DrawOptionsTooltip(tx,ty,tw,th,"hud")
					EndIf
					
					y = y + 30*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Enable console:")
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale)
						DrawOptionsTooltip(tx,ty,tw,th,"consoleenable")
					EndIf
					
					y = y + 30*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Open console on error:")
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale)
						DrawOptionsTooltip(tx,ty,tw,th,"consoleerror")
					EndIf
					
					y = y + 50*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Achievement popups:")
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale)
						DrawOptionsTooltip(tx,ty,tw,th,"achpopup")
					EndIf
					
					y = y + 50*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Show FPS:")
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale)
						DrawOptionsTooltip(tx,ty,tw,th,"opt\ShowFPS")
					EndIf
					
					y = y + 30*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Framelimit:")
					
					Color 255,255,255
					If CurrFrameLimit>0.0
						Color 255,255,0
						AAText(x + 5 * MenuScale, y + 25 * MenuScale, Framelimit%+" FPS")
					EndIf
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale)
						DrawOptionsTooltip(tx,ty,tw,th,"framelimit",Framelimit)
					EndIf
					If MouseOn(x+150*MenuScale,y+30*MenuScale,100*MenuScale,20)
						DrawOptionsTooltip(tx,ty,tw,th,"framelimit",Framelimit)
					EndIf
					
					y = y + 80*MenuScale
					
					Color 255,255,255
					AAText(x, y, "Antialiased text:")
					If MouseOn(x+270*MenuScale,y+MenuScale,20*MenuScale,20*MenuScale)
						DrawOptionsTooltip(tx,ty,tw,th,"antialiastext")
					EndIf
					;[End Block]
			End Select
		ElseIf AchievementsMenu <= 0 And OptionsMenu <= 0 And QuitMSG > 0 And KillTimer >= 0
;			Local QuitButton% = 60 
;			If SelectedDifficulty\saveType = SAVEONQUIT Lor SelectedDifficulty\saveType = SAVEANYWHERE Then
;				Local RN$ = PlayerRoom\RoomTemplate\Name$
;				Local AbleToSave% = True
;				If RN$ = "173" Lor RN$ = "exit1" Lor RN$ = "gatea" Then AbleToSave = False
;				If (Not CanSave) Then AbleToSave = False
;				If AbleToSave
;					QuitButton = 140
;				EndIf
;			EndIf
		Else
			If AchievementsMenu>0 Then
				For i=0 To 11
					If i+((AchievementsMenu-1)*12)<MAXACHIEVEMENTS Then
						DrawAchvIMG(AchvXIMG,y+((i/4)*120*MenuScale),i+((AchievementsMenu-1)*12))
					Else
						Exit
					EndIf
				Next
				
				For i=0 To 11
					If i+((AchievementsMenu-1)*12)<MAXACHIEVEMENTS Then
						If MouseOn(AchvXIMG+((i Mod 4)*SeparationConst),y+((i/4)*120*MenuScale),64*scale,64*scale) Then
							AchievementTooltip(i+((AchievementsMenu-1)*12))
							Exit
						EndIf
					Else
						Exit
					EndIf
				Next
				
			EndIf
		EndIf
		
		y = y+10
		
		If AchievementsMenu<=0 And OptionsMenu<=0 And QuitMSG<=0 Then
			If KillTimer >= 0 Then	
				
				y = y+ 72*MenuScale
				
				y = y + 75*MenuScale
				If (Not SelectedDifficulty\permaDeath) Then
					If GameSaved Then
						
					Else
						DrawFrame(x,y,390*MenuScale, 60*MenuScale)
						Color (100, 100, 100)
						AASetFont fo\Font[Font_Menu]
						AAText(x + (390*MenuScale) / 2, y + (60*MenuScale) / 2, "Load Game", True, True)
					EndIf
					y = y + 75*MenuScale
				EndIf
				
				y = y + 75*MenuScale
				
				y = y + 75*MenuScale
			Else
				y = y+104*MenuScale
				If GameSaved And (Not SelectedDifficulty\permaDeath) Then
					
				Else
					Color 50,50,50
					AAText(x + 185*MenuScale, y + 30*MenuScale, "Load Game", True, True)
				EndIf
				y= y + 80*MenuScale
			EndIf
			
			If KillTimer >= 0 And (Not MainMenuOpen)
				
			EndIf
			
			AASetFont fo\Font[Font_Default]
			If KillTimer < 0 Then RowText(DeathMSG$, x, y + 80*MenuScale, 390*MenuScale, 600*MenuScale)
		EndIf
		
		DrawAllMenuButtons()
		DrawAllMenuTicks()
		DrawAllMenuInputBoxes()
		DrawAllMenuSlideBars()
		DrawAllMenuSliders()
		
		If opt\DisplayMode=2 Then DrawImage CursorIMG, ScaledMouseX(),ScaledMouseY()
	EndIf
	
	AASetFont fo\Font[Font_Default]
	
	CatchErrors("DrawMenu")
End Function

Function UpdateMPMenu()
	CatchErrors("Uncaught (UpdateMenu)")
	
	Local x%, y%, width%, height%
	
	If MenuOpen
		If ShouldDeleteGadgets
			DeleteMenuGadgets()
		EndIf
		ShouldDeleteGadgets = False
		
		InvOpen = False
		ConsoleOpen = False
		
		width = ImageWidth(PauseMenuIMG)
		height = ImageHeight(PauseMenuIMG)
		x = opt\GraphicWidth / 2 - width / 2
		y = opt\GraphicHeight / 2 - height / 2
		
		x = x+132*MenuScale
		y = y+122*MenuScale	
		
		If (Not MouseDown1)
			OnSliderID = 0
		EndIf
		
		Local AchvXIMG% = (x + (22*MenuScale))
		Local scale# = opt\GraphicHeight/768.0
		Local SeparationConst% = 76*scale
		Local imgsize% = 64
		
		If AchievementsMenu <= 0 And OptionsMenu <= 0 And QuitMSG <= 0
			
		ElseIf AchievementsMenu <= 0 And OptionsMenu > 0 And QuitMSG <= 0 And KillTimer >= 0
			If DrawButton(x + 101 * MenuScale, y + 390 * MenuScale, 230 * MenuScale, 60 * MenuScale, "Back") Then
				AchievementsMenu = 0
				OptionsMenu = 0
				QuitMSG = 0
				MouseHit1 = False
				SaveOptionsINI()
				
				AntiAlias Opt_AntiAlias
				TextureLodBias TextureFloat#
				ShouldDeleteGadgets = True
			EndIf
			
			If DrawButton(x-5*MenuScale,y,100*MenuScale,30*MenuScale,"GRAPHICS",False)
				OptionsMenu = 1
				ShouldDeleteGadgets = True
			EndIf
			If DrawButton(x+105*MenuScale,y,100*MenuScale,30*MenuScale,"AUDIO",False)
				OptionsMenu = 2
				ShouldDeleteGadgets = True
			EndIf
			If DrawButton(x+215*MenuScale,y,100*MenuScale,30*MenuScale,"CONTROLS",False)
				OptionsMenu = 3
				ShouldDeleteGadgets = True
			EndIf
			If DrawButton(x+325*MenuScale,y,100*MenuScale,30*MenuScale,"ADVANCED",False)
				OptionsMenu = 4
				ShouldDeleteGadgets = True
			EndIf
			
			Local tx# = (opt\GraphicWidth/2)+(width/2)
			Local ty# = y
			Local tw# = 400*MenuScale
			Local th# = 150*MenuScale
			
			Select OptionsMenu
				Case 1 ;Graphics
					;[Block]
					y=y+50*MenuScale
					
					Vsync% = DrawTick(x + 270 * MenuScale, y + MenuScale, Vsync%)
					
					y=y+30*MenuScale
					
					Opt_AntiAlias = DrawTick(x + 270 * MenuScale, y + MenuScale, Opt_AntiAlias%)
					
					y=y+30*MenuScale
					
					opt\EnableRoomLights = DrawTick(x + 270 * MenuScale, y + MenuScale, opt\EnableRoomLights)
					
					y=y+30*MenuScale
					
					ScreenGamma = (SlideBar(x + 270*MenuScale, y+6*MenuScale, 100*MenuScale, ScreenGamma*50.0)/50.0)
					
					y=y+50*MenuScale
					
					ParticleAmount = Slider3(x+270*MenuScale,y+6*MenuScale,100*MenuScale,ParticleAmount,2,"MINIMAL","REDUCED","FULL")
					
					y=y+50*MenuScale
					
					opt\TextureDetails = Slider5(x+270*MenuScale,y+6*MenuScale,100*MenuScale,opt\TextureDetails,3,"0.8","0.4","0.0","-0.4","-0.8")
					Select opt\TextureDetails%
						Case 0
							TextureFloat# = 0.8
						Case 1
							TextureFloat# = 0.4
						Case 2
							TextureFloat# = 0.0
						Case 3
							TextureFloat# = -0.4
						Case 4
							TextureFloat# = -0.8
					End Select
					TextureLodBias TextureFloat
					
					y=y+50*MenuScale
					
					RenderCubeMapMode = Slider3(x+270*MenuScale,y+6*MenuScale,100*MenuScale,RenderCubeMapMode,4,"OFF","MODE 1","MODE 2")
					
					y=y+50*MenuScale
					
					Local SlideBarFOV# = FOV#-40
                    SlideBarFOV = (SlideBar(x + 270*MenuScale, y+6*MenuScale,100*MenuScale, SlideBarFOV*2.0)/2.0)
                    FOV = SlideBarFOV+40
					CameraZoom(Camera, Min(1.0+(CurrCameraZoom/400.0),1.1) / (Tan((2*ATan(Tan((FOV#)/2)*(Float(RealGraphicWidth)/Float(RealGraphicHeight))))/2.0)))
					;[End Block]
				Case 2 ;Audio
					;[Block]
					y = y + 50*MenuScale
					
					MusicVolume = (SlideBar(x + 250*MenuScale, y-4*MenuScale, 100*MenuScale, MusicVolume*100.0)/100.0)
					
					y = y + 30*MenuScale
					
					PrevSFXVolume = (SlideBar(x + 250*MenuScale, y-4*MenuScale, 100*MenuScale, opt\SFXVolume*100.0)/100.0)
					If (Not DeafPlayer) Then opt\SFXVolume# = PrevSFXVolume#
					
					y = y + 30*MenuScale
					
					opt\EnableSFXRelease = DrawTick(x + 270 * MenuScale, y + MenuScale, opt\EnableSFXRelease,True)
					
					y = y + 30*MenuScale
					
					EnableUserTracks = DrawTick(x + 270 * MenuScale, y + MenuScale, EnableUserTracks,True)
					
					If EnableUserTracks
						y = y + 30 * MenuScale
						UserTrackMode = DrawTick(x + 270 * MenuScale, y + MenuScale, UserTrackMode)
					EndIf
					;[End Block]
				Case 3 ;Controls
					;[Block]
					y = y + 50*MenuScale
					
					MouseSens = (SlideBar(x + 270*MenuScale, y-4*MenuScale, 100*MenuScale, (MouseSens+0.5)*100.0)/100.0)-0.5
					
					y = y + 30*MenuScale
					
					InvertMouse = DrawTick(x + 270 * MenuScale, y + MenuScale, InvertMouse)
					
					y = y + 30*MenuScale
					
					y = y + 10*MenuScale
					
					InputBox(x + 200 * MenuScale, y + 20 * MenuScale,100*MenuScale,20*MenuScale,KeyName(Min(KEY_UP,210)),5)		
					
					InputBox(x + 200 * MenuScale, y + 40 * MenuScale,100*MenuScale,20*MenuScale,KeyName(Min(KEY_LEFT,210)),3)	
					
					InputBox(x + 200 * MenuScale, y + 60 * MenuScale,100*MenuScale,20*MenuScale,KeyName(Min(KEY_DOWN,210)),6)				
					
					InputBox(x + 200 * MenuScale, y + 80 * MenuScale,100*MenuScale,20*MenuScale,KeyName(Min(KEY_RIGHT,210)),4)
					
					
					InputBox(x + 200 * MenuScale, y + 100 * MenuScale,100*MenuScale,20*MenuScale,KeyName(Min(KEY_BLINK,210)),7)				
					
					InputBox(x + 200 * MenuScale, y + 120 * MenuScale,100*MenuScale,20*MenuScale,KeyName(Min(KEY_SPRINT,210)),8)
					
					InputBox(x + 200 * MenuScale, y + 140 * MenuScale,100*MenuScale,20*MenuScale,KeyName(Min(KEY_INV,210)),9)
					
					InputBox(x + 200 * MenuScale, y + 160 * MenuScale,100*MenuScale,20*MenuScale,KeyName(Min(KEY_CROUCH,210)),10)
					
					InputBox(x + 200 * MenuScale, y + 180 * MenuScale,100*MenuScale,20*MenuScale,KeyName(Min(KEY_SAVE,210)),11)	
					
					InputBox(x + 200 * MenuScale, y + 200 * MenuScale,100*MenuScale,20*MenuScale,KeyName(Min(KEY_CONSOLE,210)),12)
					
					
					For i = 0 To 227
						If KeyHit(i) Then key = i : Exit
					Next
					If key <> 0 Then
						Select SelectedInputBox
							Case 3
								KEY_LEFT = key
							Case 4
								KEY_RIGHT = key
							Case 5
								KEY_UP = key
							Case 6
								KEY_DOWN = key
							Case 7
								KEY_BLINK = key
							Case 8
								KEY_SPRINT = key
							Case 9
								KEY_INV = key
							Case 10
								KEY_CROUCH = key
							Case 11
								KEY_SAVE = key
							Case 12
								KEY_CONSOLE = key
						End Select
						SelectedInputBox = 0
					EndIf
					;[End Block]
				Case 4 ;Advanced
					;[Block]
					y = y + 50*MenuScale
					
					HUDenabled = DrawTick(x + 270 * MenuScale, y + MenuScale, HUDenabled)
					
					y = y + 30*MenuScale
					
					opt\CanOpenConsole = DrawTick(x +270 * MenuScale, y + MenuScale, opt\CanOpenConsole)
					
					y = y + 30*MenuScale
					
					opt\ConsoleOpening = DrawTick(x + 270 * MenuScale, y + MenuScale, opt\ConsoleOpening)
					
					y = y + 50*MenuScale
					
					AchvMSGenabled% = DrawTick(x + 270 * MenuScale, y, AchvMSGenabled%)
					
					y = y + 50*MenuScale
					
					opt\ShowFPS% = DrawTick(x + 270 * MenuScale, y, opt\ShowFPS%)
					
					y = y + 30*MenuScale
					
					Local prevCurrFrameLimit = (CurrFrameLimit>0.0)
					
					If DrawTick(x + 270 * MenuScale, y, CurrFrameLimit > 0.0) Then
						CurrFrameLimit# = (SlideBar(x + 150*MenuScale, y+30*MenuScale, 100*MenuScale, CurrFrameLimit#*99.0)/99.0)
						CurrFrameLimit# = Max(CurrFrameLimit, 0.01)
						Framelimit% = 19+(CurrFrameLimit*100.0)
					Else
						CurrFrameLimit# = 0.0
						Framelimit = 0
					EndIf
					
					If prevCurrFrameLimit
						If prevCurrFrameLimit<>CurrFrameLimit
							ShouldDeleteGadgets=True
						EndIf
					EndIf
					
					y = y + 80*MenuScale
					
					AATextEnable% = DrawTick(x + 270 * MenuScale, y + MenuScale, AATextEnable%)
					If AATextEnable_Prev% <> AATextEnable
						For font.AAFont = Each AAFont
							FreeFont font\lowResFont%
							If (Not AATextEnable)
								DeleteSingleTextureEntryFromCache font\texture
								FreeImage font\backup
							EndIf
							Delete font
						Next
						If (Not AATextEnable) Then
							FreeEntity AATextCam
							;For i%=0 To 149
							;	FreeEntity AATextSprite[i]
							;Next
						EndIf
						InitAAFont()
						InitFonts()
						;ReloadAAFont()
						AATextEnable_Prev% = AATextEnable
					EndIf
					;[End Block]
			End Select
		ElseIf AchievementsMenu <= 0 And OptionsMenu <= 0 And QuitMSG > 0 And KillTimer >= 0
			Local QuitButton% = 60 
			If DrawButton(x, y + QuitButton*MenuScale, 390*MenuScale, 60*MenuScale, "Yes") Then
				NullMPGame()
				MenuOpen = False
				MainMenuOpen = True
				MainMenuTab = MenuTab_Serverlist
				FlushKeys()
				Return
			EndIf
			QuitButton = 140
			If DrawButton(x, y + QuitButton*MenuScale, 390*MenuScale, 60*MenuScale, "No") Then
				AchievementsMenu = 0
				OptionsMenu = 0
				QuitMSG = 0
				MouseHit1 = False
				ShouldDeleteGadgets = True
			EndIf
		Else
			If DrawButton(x+101*MenuScale, y + 344*MenuScale, 230*MenuScale, 60*MenuScale, "Back") Then
				AchievementsMenu = 0
				OptionsMenu = 0
				QuitMSG = 0
				MouseHit1 = False
				ShouldDeleteGadgets = True
			EndIf
			
			If AchievementsMenu>0 Then
				If AchievementsMenu <= Floor(Float(MAXACHIEVEMENTS-1)/12.0) Then 
					If DrawButton(x+341*MenuScale, y + 344*MenuScale, 50*MenuScale, 60*MenuScale, ">") Then
						AchievementsMenu = AchievementsMenu+1
						ShouldDeleteGadgets=True
					EndIf
				EndIf
				If AchievementsMenu > 1 Then
					If DrawButton(x+41*MenuScale, y + 344*MenuScale, 50*MenuScale, 60*MenuScale, "<") Then
						AchievementsMenu = AchievementsMenu-1
						ShouldDeleteGadgets=True
					EndIf
				EndIf
			EndIf
		EndIf
		
		y = y+10
		
		If AchievementsMenu<=0 And OptionsMenu<=0 And QuitMSG<=0 Then
			y = y+ 72*MenuScale
			
			If DrawButton(x, y, 390*MenuScale, 60*MenuScale, "Resume", True, True) Then
				MenuOpen = False
				ResumeSounds()
				MouseXSpeed() : MouseYSpeed() : MouseZSpeed() : mouse_x_speed_1#=0.0 : mouse_y_speed_1#=0.0
				DeleteMenuGadgets()
				Return
			EndIf
			
			y = y + 75*MenuScale
			If DrawButton(x, y, 390*MenuScale, 60*MenuScale, "Options")
				OptionsMenu = 1
				ShouldDeleteGadgets = True
			EndIf
			y = y + 75*MenuScale
			
			If KillTimer >= 0 And (Not MainMenuOpen)
				If DrawButton(x, y, 390*MenuScale, 60*MenuScale, "Disconnect") Then
					QuitMSG = 1
					ShouldDeleteGadgets = True
				EndIf
			EndIf
		EndIf
		
	EndIf
	
	CatchErrors("UpdateMenu")
End Function

Type EnemySpawner
	Field obj%
	Field time#
	Field enemies$
End Type

Function CreateEnemySpawner.EnemySpawner(x#,y#,z#,enemyString$)
	Local ens.EnemySpawner = New EnemySpawner
	
	ens\obj = CreatePivot()
	PositionEntity ens\obj,x#,y#,z#
	ens\enemies = enemyString
	
	Return ens
End Function

Function UpdateEnemySpawners()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local ens.EnemySpawner,n.NPCs
	Local NPCtoSpawnType% = -1
	Local spawn_chance#
	
	For ens = Each EnemySpawner
		If ens\time <= 0.0 Then
			NPCtoSpawnType = -1
			Local EnemyCount% = 0
			Local EnemyCount2% = 0
			For n.NPCs = Each NPCs
				EnemyCount = EnemyCount + 1
				If (Not n\IsDead) Then
					EnemyCount2 = EnemyCount2 + 1
				EndIf
			Next
			
			If EnemyCount < 20 And EnemyCount2 < mp_I\Gamemode\EnemyCount Then ;Only spawn an enemy if the maximum amount of enemies doesn't get overexceeded
				Repeat
					spawn_chance = Rand(1,100)
					If spawn_chance < 50 Then
						If Instr(ens\enemies,"zombie")>0 Then
							If (mp_I\BossNPC = Null And (mp_I\Gamemode\Phase / 2) = mp_I\Gamemode\MaxPhase) Then
								Select mp_I\MapInList\BossNPC
									Case "SCP-035"
										NPCtoSpawnType = NPCtype035
									Case "SCP-457"
										NPCtoSpawnType = NPCtype457
								End Select
							Else
								NPCtoSpawnType = NPCtypeZombieMP
							EndIf
							Exit
						EndIf
					ElseIf spawn_chance >= 50 And spawn_chance < 70 Then
						If Instr(ens\enemies,"939")>0 Then
							If ((Float(mp_I\Gamemode\Phase / 2) / mp_I\Gamemode\MaxPhase > 0.4 And (mp_I\Gamemode\Phase/2) <> mp_I\Gamemode\MaxPhase) Lor mp_I\BossNPC <> Null) Then
								NPCtoSpawnType = NPCtype939
							Else
								NPCtoSpawnType = -1
							EndIf
							Exit
						EndIf
					Else
						If Instr(ens\enemies,"1048a")>0 Then
							If ((Float(mp_I\Gamemode\Phase / 2) / mp_I\Gamemode\MaxPhase > 0.25 And (mp_I\Gamemode\Phase/2) <> mp_I\Gamemode\MaxPhase) Lor mp_I\BossNPC <> Null) Then
								NPCtoSpawnType = NPCtype1048a
							Else
								NPCtoSpawnType = -1
							EndIf
							Exit
						EndIf
					EndIf
				Forever
				
				Select NPCtoSpawnType
					Case -1
						;Skip
					Case NPCtypeZombieMP
						If Rand(10)<10 Then
							n = CreateNPC(NPCtypeZombieMP,EntityX(ens\obj),EntityY(ens\obj)+0.5,EntityZ(ens\obj))
						Else
							n = CreateNPC(NPCtypeGuardZombieMP,EntityX(ens\obj),EntityY(ens\obj)+0.5,EntityZ(ens\obj))
						EndIf
						TeleportEntity(n\Collider,EntityX(ens\obj),EntityY(ens\obj)+0.5,EntityZ(ens\obj),n\CollRadius)
					Default
						n = CreateNPC(NPCtoSpawnType,EntityX(ens\obj),EntityY(ens\obj)+0.5,EntityZ(ens\obj))
						TeleportEntity(n\Collider,EntityX(ens\obj),EntityY(ens\obj)+0.5,EntityZ(ens\obj),n\CollRadius)
				End Select
				ens\time = 70*5 ;70*15
			EndIf
		Else
			ens\time = ens\time - FPSfactor
		EndIf
	Next
	
End Function

Function UpdateNPCsServer()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	CatchErrors("Uncaught (UpdateNPCsServer)")
	
	Local n.NPCs,i%,dist#,w.WayPoints,n2.NPCs,j%
	
	Local prevDeathState%
	
	For n = Each NPCs
		;A variable to determine if the NPC is in the facility or not
		n\InFacility = CheckForNPCInFacility(n)
		
		prevDeathState = n\IsDead
		
		Select n\NPCtype
			Case NPCtype035
				UpdateNPCtype035MP(n)
			Case NPCtype1048a
				UpdateNPCtype1048aMP(n)
			Case NPCtype457
				UpdateNPCtype457MP(n)
			Case NPCtype939
				UpdateNPCtype939MP(n)
			Case NPCtypeOldMan
				UpdateNPCtype106MP(n)
			Case NPCtypeTentacle
				UpdateNPCtypeTentacleMP(n)
			Case NPCtypeZombieMP,NPCtypeGuardZombieMP
				UpdateNPCtypeZombieMP(n)
		End Select
		
		If n<>Null Then
			If (Not n\IsDead) And (n\NPCtype<>NPCtype1048a) And (n\NPCtype<>NPCtypeTentacle) Then
				For n2.NPCs = Each NPCs
					If n2<>n And n2\IsDead=False And (n2\NPCtype<>NPCtype1048a And n2\NPCtype<>NPCtype939 And n2\NPCtype<>NPCtypeTentacle) Then
						If n2\ID>n\ID Then
							If Abs(DeltaYaw(n\Collider,n2\Collider))<80.0 Then
								If EntityDistanceSquared(n\Collider,n2\Collider)<0.49 Then							
									TranslateEntity n2\Collider, Cos(EntityYaw(n\Collider,True)+90)* 0.01 * FPSfactor, 0, Sin(EntityYaw(n\Collider,True)+90)* 0.01 * FPSfactor, True
								EndIf
							EndIf
						Else
							If EntityDistanceSquared(n\Collider,n2\Collider)<0.49 Then
								TranslateEntity n\Collider, Cos(EntityYaw(n\Collider,True)-45)* 0.01 * FPSfactor, 0, Sin(EntityYaw(n\Collider,True)-45)* 0.01 * FPSfactor, True
							EndIf
						EndIf
					EndIf
				Next
			EndIf
			
			If n\NPCtype <> NPCtypeTentacle Then
				TranslateEntity n\Collider, 0, n\DropSpeed, 0
				
				Local CollidedFloor% = False
				For i% = 1 To CountCollisions(n\Collider)
					If CollisionY(n\Collider, i) < EntityY(n\Collider) - 0.01 Then CollidedFloor = True : Exit
				Next
				
				If CollidedFloor = True Then
					n\DropSpeed# = 0
				Else
					n\DropSpeed# = Max(n\DropSpeed - 0.005*FPSfactor*n\GravityMult,-n\MaxGravity)
				EndIf
			EndIf
			
			If mp_I\BossNPC <> Null Then
				If n = mp_I\BossNPC Then
					If n\IsDead Then
						For n2 = Each NPCs
							n2\HP = 0
						Next
						mp_I\BossNPC = Null
						mp_I\Gamemode\EnemyCount = 0
						mp_I\Gamemode\Phase = Waves_End
						mp_I\Gamemode\PhaseTimer = 70*10
					EndIf
				EndIf
			EndIf
			
			n\GotHit = 0
			
			If mp_I\PlayState = GAME_SERVER Then
				If ShouldSyncNPC(n) Then
					If prevDeathState<>n\IsDead Then
						mp_I\Gamemode\EnemyCount = mp_I\Gamemode\EnemyCount - 1
					EndIf
					
					If n <> mp_I\BossNPC Then
						If n\DeleteTimer = 0.0 Then
							n\DeleteTimer = FPSfactor
							
							If Players(n\ClosestPlayer) = Null Then
								n\ClosestPlayer = GetClosestPlayerID(n)
							EndIf
							
							If EntityDistanceSquared(n\Collider,Players(n\ClosestPlayer)\Collider)>400.0 Then
								RemoveNPC(n)
							EndIf
						ElseIf n\DeleteTimer > 0.0 And n\DeleteTimer < 70*7 Then
							n\DeleteTimer = n\DeleteTimer + FPSfactor
						Else
							n\DeleteTimer = 0.0
						EndIf
					EndIf
				EndIf
			EndIf
			
			CatchErrors("A NPC (Multiplayer)")
		Else
			CatchErrors("Deleted NPC (Multiplayer)")
		EndIf
	Next
	
End Function

;Unused
;[Block]
;Function UpdateNPCsClient()
;	CatchErrors("Uncaught (UpdateNPCsClient)")
;	
;	Local n.NPCs
;	
;	For n = Each NPCs
;		Local prevFrame = AnimTime(n\obj)
;		
;		;SetNPCFrame(n,n\Frame)
;		SetAnimTime(n\obj,n\Frame)
;		
;		Select n\NPCtype
;			Case NPCtypeZombieMP,NPCtypeGuardZombieMP
;				UpdateNPCtypeZombieMPClient(n)
;			Case NPCtypeOldMan
;				UpdateNPCtype106Client(n)
;			Case NPCtype939
;				UpdateNPCtype939Client(n)
;			Case NPCtype1048a
;				UpdateNPCtype1048aClient(n)
;		End Select
;		
;		;If n<>Null Then
;		;	RotateEntity n\Collider, 0, CurveAngle(n\Angle, EntityYaw(n\Collider), 10.0), 0
;		;EndIf
;		
;;		If n<>Null Then
;;			If n\IsDead
;;				EntityType n\Collider,HIT_DEAD
;;			Else
;;				EntityType n\Collider,HIT_PLAYER
;;			EndIf
;;		EndIf
;	Next
;	
;	CatchErrors("UpdateNPCsClient")
;End Function
;[End Block]
;[Block]
;Function RemoveMPNPC(n.NPCs)
;	
;	If n=Null Then Return
;	
;	If n\obj2 <> 0 Then 
;		FreeEntity n\obj2
;		n\obj2 = 0
;	EndIf
;	If n\obj3 <> 0 Then 
;		FreeEntity n\obj3
;		n\obj3 = 0
;	EndIf
;	If n\obj4 <> 0 Then 
;		FreeEntity n\obj4
;		n\obj4 = 0
;	EndIf
;	
;	If (Not n\SoundChn_IsStream)
;		If (n\SoundChn <> 0 And ChannelPlaying(n\SoundChn)) Then
;			StopChannel(n\SoundChn)
;		EndIf
;	Else
;		If (n\SoundChn <> 0)
;			StopStream_Strict(n\SoundChn)
;		EndIf
;	EndIf
;	
;	If (Not n\SoundChn2_IsStream)
;		If (n\SoundChn2 <> 0 And ChannelPlaying(n\SoundChn2)) Then
;			StopChannel(n\SoundChn2)
;		EndIf
;	Else
;		If (n\SoundChn2 <> 0)
;			StopStream_Strict(n\SoundChn2)
;		EndIf
;	EndIf
;	
;	If n\Sound<>0 Then FreeSound_Strict n\Sound
;	If n\Sound2<>0 Then FreeSound_Strict n\Sound2
;	
;	FreeEntity(n\obj) : n\obj = 0
;	FreeEntity(n\Collider) : n\Collider = 0	
;	
;	Delete n
;End Function
;[End Block]

Function UpdatePlayerGunsServer()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local i%
	
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			If Players(i)\Team > Team_Spectator Then
				UpdatePlayerGun(i)
			EndIf
		EndIf
	Next
	
End Function

Function UpdatePlayerGun(playerID%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local g.Guns
	
	Local DeployTime#,Accuracy#,DamageOnEntity%,RateOfFire#,MaxAmmo%,ReloadTime#,ReloadStartTime#
	Local GunType%,ShootAmount%,AttackDelay#,Range#
	
	Local i%
	
	If Players(playerID)\CurrHP > 0 Then
		
		Players(playerID)\PrevShootState = Players(playerID)\ShootState
		
		For g = Each Guns
			If g\ID = Players(playerID)\WeaponInSlot[Players(playerID)\SelectedSlot] Then
				DeployTime = g\Deploy_Time
				Accuracy = g\Accuracy
				DamageOnEntity = g\DamageOnEntity
				RateOfFire = g\Rate_Of_Fire
				MaxAmmo = g\MaxCurrAmmo
				ReloadTime = g\Reload_Time
				ReloadStartTime = g\Reload_Start_Time
				GunType = g\GunType
				ShootAmount = g\Amount_Of_Bullets
				AttackDelay = g\ShootDelay
				Range = g\Range
				Exit
			EndIf
		Next
		
		If Players(playerID)\DeployState < DeployTime Then
			Players(playerID)\DeployState = Players(playerID)\DeployState + FPSfactor
		Else
			If Players(playerID)\IsPlayerSprinting Then
				Players(playerID)\PressMouse1 = False
			EndIf
			
			If GunType = GUNTYPE_SHOTGUN Then
				If Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot]=0 Then
					Players(playerID)\PressReload = False
				EndIf
				
				If (Players(playerID)\PressMouse1 And Players(playerID)\ShootState = 0.0) Then
					If Players(playerID)\Ammo[Players(playerID)\SelectedSlot] > 0 Then
						If mp_I\PlayState = GAME_SERVER Then
							For i=1 To ShootAmount
								ShootGunMP(playerID,Accuracy,DamageOnEntity)
							Next
						EndIf
						Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = Players(playerID)\Ammo[Players(playerID)\SelectedSlot] - 1
						Players(playerID)\ShootState = FPSfactor
						Players(playerID)\ReloadState = 0.0
						Players(playerID)\PressReload = False
					EndIf
				EndIf
				If Players(playerID)\ShootState > 0.0 And Players(playerID)\ShootState < RateOfFire Then
					Players(playerID)\ShootState = Players(playerID)\ShootState + FPSfactor
					Players(playerID)\ReloadState = 0.0
					Players(playerID)\PressReload = False
				Else
					Players(playerID)\ShootState = 0.0
				EndIf
				If Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = MaxAmmo Lor Players(playerID)\ReloadState > 0.0 Then
					Players(playerID)\PressReload = False
				EndIf
				If Players(playerID)\PressReload Then
					Players(playerID)\ReloadState = FPSfactor
				EndIf
				
				If Players(playerID)\ReloadState > 0.0 And Players(playerID)\ReloadState < ReloadStartTime Then
					Players(playerID)\ShootState = 0.0
					Players(playerID)\ReloadState = Players(playerID)\ReloadState + FPSfactor
					If Players(playerID)\ReloadState >= ReloadStartTime Then
						Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = Players(playerID)\Ammo[Players(playerID)\SelectedSlot] + 1
						Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot] = Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot] - 1
					EndIf
				ElseIf Players(playerID)\ReloadState >= ReloadStartTime And Players(playerID)\ReloadState < (ReloadStartTime+ReloadTime) Then
					Players(playerID)\ReloadState = Players(playerID)\ReloadState + FPSfactor
					If Players(playerID)\ReloadState >= (ReloadStartTime+ReloadTime) Then
						If Players(playerID)\Ammo[Players(playerID)\SelectedSlot] < MaxAmmo And Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot] > 0 Then
							Players(playerID)\ReloadState = ReloadStartTime
							Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = Players(playerID)\Ammo[Players(playerID)\SelectedSlot] + 1
							Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot] = Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot] - 1
						EndIf
					EndIf
				Else
					Players(playerID)\ReloadState = 0.0
				EndIf
			ElseIf GunType = GUNTYPE_MELEE Then
				Players(playerID)\PressReload = False
				
				If (Players(playerID)\PressMouse1 And Players(playerID)\ShootState = 0.0) Then
					Players(playerID)\ShootState = FPSfactor
				EndIf
				If Players(playerID)\ShootState > 0.0 And Players(playerID)\ShootState < RateOfFire Then
					Players(playerID)\ShootState = Players(playerID)\ShootState + FPSfactor
					If Players(playerID)\ShootState >= AttackDelay And Players(playerID)\ShootState <= AttackDelay+FPSfactor Then
						If mp_I\PlayState = GAME_SERVER Then
							For i=1 To ShootAmount
								ShootGunMP(playerID,Accuracy,DamageOnEntity,Range)
							Next
						EndIf
					EndIf
				Else
					Players(playerID)\ShootState = 0.0
				EndIf
			Else
				If Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot]=0 Then
					Players(playerID)\PressReload = False
				EndIf
				
				If Players(playerID)\ReloadState = 0.0 Then
					If Players(playerID)\PressMouse1 Lor Players(playerID)\ShootState = -1.0 Then
						If Players(playerID)\ShootState = 0.0 Lor Players(playerID)\ShootState = -1.0 Then
							If Players(playerID)\Ammo[Players(playerID)\SelectedSlot] > 0 Then
								If mp_I\PlayState = GAME_SERVER Then
									For i=1 To ShootAmount
										ShootGunMP(playerID,Accuracy,DamageOnEntity)
									Next
								EndIf
								Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = Players(playerID)\Ammo[Players(playerID)\SelectedSlot] - 1
								Players(playerID)\ShootState = FPSfactor
							EndIf
						EndIf
					EndIf
					If Players(playerID)\ShootState > 0.0 And Players(playerID)\ShootState < RateOfFire Then
						Players(playerID)\ShootState = Players(playerID)\ShootState + FPSfactor
					Else
						Players(playerID)\ShootState = 0.0
					EndIf
					If Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = MaxAmmo Lor Players(playerID)\ReloadState > 0.0 Then
						Players(playerID)\PressReload = False
					EndIf
					If Players(playerID)\PressReload Then
						Players(playerID)\ReloadState = FPSfactor
					EndIf
				ElseIf Players(playerID)\ReloadState > 0.0 And Players(playerID)\ReloadState < ReloadTime Then
					Players(playerID)\ShootState = 0.0
					Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = 0
					Players(playerID)\ReloadState = Players(playerID)\ReloadState + FPSfactor
				Else
					Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = MaxAmmo
					Players(playerID)\ReloadState = 0.0
					Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot] = Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot] - 1
				EndIf
			EndIf
		EndIf
	EndIf
	
End Function

Function UpdateGunsClient()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local g.Guns,g2.Guns,p.Particles
	Local shooting% = False
	Local g_I.GunInstance = First GunInstance
	Local mpl.MainPlayer = First MainPlayer
	
	Local campitch# = EntityPitch(mpl\CameraPivot)+180
	Local camyaw# = EntityYaw(mpl\CameraPivot)
	Local gpivotpitch# = EntityPitch(g_I\GunPivot)+180
	Local gpivotyaw# = EntityYaw(g_I\GunPivot)
	Local pitch# = Clamp(CurveAngle(campitch, gpivotpitch, 10.0), campitch-5, campitch+5)
	Local yaw# = CurveAngle(camyaw, gpivotyaw, 10.0)
	
	yaw = ClampAngle(yaw, camyaw, 5)
	
	RotateEntity g_I\GunPivot,pitch-180, yaw, 0
	
	g_I\GunAnimFLAG = True
	UsingScope% = False
	IsPlayerShooting% = False
	
	ShowEntity g_I\GunPivot
	
	If g_I\GunLightTimer > 0.0 And g_I\GunLightTimer < 2.5 Then
		g_I\GunLightTimer = g_I\GunLightTimer + FPSfactor
		ShowEntity g_I\GunLight
	Else
		g_I\GunLightTimer = 0.0
		HideEntity g_I\GunLight
	EndIf
	
	Local prevFrame#
	
	Players(mp_I\PlayerID)\PressMouse1=False
	
	If Players(mp_I\PlayerID)\CurrHP > 0 Then
		For g.Guns = Each Guns
			HideEntity g\MuzzleFlash
			
			If g_I\GunChangeFLAG = False Then
				For g2.Guns = Each Guns
					If g2\ID%<>Players(mp_I\PlayerID)\WeaponInSlot[Players(mp_I\PlayerID)\SelectedSlot] Then
						SetAnimTime g2\obj,0
						HideEntity g2\obj
					Else
						ShowEntity g2\obj
					EndIf
				Next
				DeselectIronSight()
				Players(mp_I\PlayerID)\DeployState = 0
				Players(mp_I\PlayerID)\ReloadState = 0
				Players(mp_I\PlayerID)\ShootState = 0
				Players(mp_I\PlayerID)\PressMouse1 = False
				Players(mp_I\PlayerID)\PressReload = False
				MouseHit1 = False
				MouseDown1 = False
				MouseHit(1)
				g_I\HoldingGun = Players(mp_I\PlayerID)\WeaponInSlot[Players(mp_I\PlayerID)\SelectedSlot]
				g_I\GunChangeFLAG = True
			EndIf
			
			prevFrame# = AnimTime(g\obj)
			
			Select g\GunType
				Case GUNTYPE_AUTO
					;[Block]
					If g\ID=Players(mp_I\PlayerID)\WeaponInSlot[Players(mp_I\PlayerID)\SelectedSlot] Then
						If Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot]=0 Then
							If MouseHit1 And (Not MenuOpen) And (Not ConsoleOpen) And (Not InTeamSelection()) Then
								Players(mp_I\PlayerID)\PressMouse1=True
								Players(mp_I\PlayerID)\PressReload=False
							EndIf
						Else
							If MouseDown1 And (Not MenuOpen) And (Not ConsoleOpen) And (Not InTeamSelection()) Then
								Players(mp_I\PlayerID)\PressMouse1=True
								Players(mp_I\PlayerID)\PressReload=False
							EndIf
						EndIf
						
						If (Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot]=0 And Players(mp_I\PlayerID)\ShootState > 0.0) Lor Players(mp_I\PlayerID)\IsPlayerSprinting Then
							Players(mp_I\PlayerID)\PressMouse1=False
						EndIf
						
						If Players(mp_I\PlayerID)\ShootState = 0.0 And Players(mp_I\PlayerID)\PressMouse1 And Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] > 0 Then
							SetAnimTime(g\obj,g\Frame_Idle)
						EndIf
						
						If Players(mp_I\PlayerID)\DeployState < g\Deploy_Time Lor Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] = g\MaxCurrAmmo Lor Players(mp_I\PlayerID)\ReloadState = 0.0 Then
							Players(mp_I\PlayerID)\PressReload=False
						EndIf
						If Players(mp_I\PlayerID)\ReloadAmmo[Players(mp_I\PlayerID)\SelectedSlot]=0 Then
							Players(mp_I\PlayerID)\PressReload=False
						EndIf
						
						If KeyHit(KEY_RELOAD) And (Not MenuOpen) And (Not ConsoleOpen) Then
							If Players(mp_I\PlayerID)\ReloadState = 0.0 And Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] < g\MaxCurrAmmo And (Not Players(mp_I\PlayerID)\IronSight) Then
								Players(mp_I\PlayerID)\PressReload=True
							EndIf
						EndIf
						
						shooting = False
						If Players(mp_I\PlayerID)\DeployState < g\Deploy_Time Then
							ChangeGunFrames(g,g\Anim_Deploy,False)
							If prevFrame# < (g\Anim_Deploy\x+1) And AnimTime(g\obj) >= (g\Anim_Deploy\x+1) Then
								PlayGunSound(g\name+"\deploy",1,1,False)
							EndIf
						Else
							If Players(mp_I\PlayerID)\ReloadState = 0.0 Then
								If Players(mp_I\PlayerID)\ShootState = 0.0 Then
									If AnimTime(g\obj) > g\Anim_Shoot\x And AnimTime(g\obj) < g\Anim_Shoot\y-0.5 Then
										ChangeGunFrames(g,g\Anim_Shoot,False)
									Else
										If Players(mp_I\PlayerID)\IsPlayerSprinting Then
											Players(mp_I\PlayerID)\PressMouse1 = False
											Players(mp_I\PlayerID)\PressReload = False
											
											If AnimTime(g\obj)<=(g\Anim_Sprint_Transition\y-0.5) Lor AnimTime(g\obj)>(g\Anim_Sprint_Cycle\y) Then
												ChangeGunFrames(g,g\Anim_Sprint_Transition,False)
											Else
												ChangeGunFrames(g,g\Anim_Sprint_Cycle,True)
											EndIf
										Else
											If AnimTime(g\obj)>(g\Anim_Sprint_Transition\x+0.5) And AnimTime(g\obj)<=g\Anim_Sprint_Cycle\y Then
												ChangeGunFrames(g,g\Anim_Sprint_Transition,False,True)
											Else
												SetAnimTime(g\obj,g\Frame_Idle)
											EndIf
										EndIf
									EndIf
									
									If Players(mp_I\PlayerID)\PressMouse1 And Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot]=0 Then
										PlaySound_Strict g_I\ShootEmptySFX
									EndIf
								Else
									ChangeGunFrames(g,g\Anim_Shoot,False)
									If Ceil(AnimTime(g\obj)) = g\Anim_Shoot\x Then
										PlayGunSound(g\name,g\MaxShootSounds,0,True)
										CameraShake = g\Knockback/2.0
										user_camera_pitch = user_camera_pitch - g\Knockback
										g_I\GunLightTimer = FPSfactor
										shooting = True
										ShowEntity g\MuzzleFlash
										TurnEntity g\MuzzleFlash,0,0,Rnd(360)
										ScaleSprite g\MuzzleFlash,Rnd(0.025,0.03),Rnd(0.025,0.03)
									EndIf
									If Players(mp_I\PlayerID)\ShootState >= g\Rate_Of_Fire-FPSfactor And Players(mp_I\PlayerID)\PressMouse1 Then
										SetAnimTime g\obj,g\Anim_Shoot\x
									EndIf
								EndIf
							Else
								If AnimTime(g\obj)>g\Anim_Reload\y Lor AnimTime(g\obj)<g\Anim_Reload\x Then
									SetAnimTime(g\obj,g\Anim_Reload\x)
								EndIf
								ChangeGunFrames(g,g\Anim_Reload,False)
								If prevFrame# < (g\Anim_Reload\x+1) And AnimTime(g\obj) >= (g\Anim_Reload\x+1) Then
									PlayGunSound(g\name+"\reload",g\MaxReloadSounds,1,False)
								ElseIf prevFrame# < (g\Anim_Reload\y-0.5) And AnimTime(g\obj) >= (g\Anim_Reload\y-0.5) Then
									Players(mp_I\PlayerID)\PressReload = False
								EndIf
								Players(mp_I\PlayerID)\IronSight = False
								g_I\IronSight = False
							EndIf
						EndIf
						
						If (Not Players(mp_I\PlayerID)\IsPlayerSprinting) And (Not shooting) Then
							g_I\GunAnimFLAG = False
						Else
							g_I\GunAnimFLAG = True
						EndIf
					EndIf
					;[End Block]
				Case GUNTYPE_SEMI
					;[Block]
					If g\ID=Players(mp_I\PlayerID)\WeaponInSlot[Players(mp_I\PlayerID)\SelectedSlot] Then
						If MouseHit1 And (Not MenuOpen) And (Not ConsoleOpen) And (Not InTeamSelection()) Then
							Players(mp_I\PlayerID)\PressMouse1=True
							Players(mp_I\PlayerID)\PressReload=False
						EndIf
						
						If (Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot]=0 And Players(mp_I\PlayerID)\ShootState > 0.0) Lor Players(mp_I\PlayerID)\IsPlayerSprinting Then
							Players(mp_I\PlayerID)\PressMouse1=False
						EndIf
						
						If Players(mp_I\PlayerID)\ShootState = 0.0 And Players(mp_I\PlayerID)\PressMouse1 And Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] > 0 Then
							SetAnimTime(g\obj,g\Frame_Idle)
						EndIf
						
						If Players(mp_I\PlayerID)\DeployState < g\Deploy_Time Lor Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] = g\MaxCurrAmmo Lor Players(mp_I\PlayerID)\ReloadState = 0.0 Then
							Players(mp_I\PlayerID)\PressReload=False
						EndIf
						If Players(mp_I\PlayerID)\ReloadAmmo[Players(mp_I\PlayerID)\SelectedSlot]=0 Then
							Players(mp_I\PlayerID)\PressReload=False
						EndIf
						
						If KeyHit(KEY_RELOAD) And (Not MenuOpen) And (Not ConsoleOpen) Then
							If Players(mp_I\PlayerID)\ReloadState = 0.0 And Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] < g\MaxCurrAmmo And (Not Players(mp_I\PlayerID)\IronSight) Then
								Players(mp_I\PlayerID)\PressReload=True
							EndIf
						EndIf
						
						shooting = False
						If Players(mp_I\PlayerID)\DeployState < g\Deploy_Time Then
							If Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] = 0 Then
								ChangeGunFrames(g,g\Anim_NoAmmo_Deploy,False)
							Else
								ChangeGunFrames(g,g\Anim_Deploy,False)
							EndIf
							If prevFrame# < (g\Anim_Deploy\x+1) And AnimTime(g\obj) >= (g\Anim_Deploy\x+1) Then
								PlayGunSound(g\name+"\deploy",1,1,False)
							EndIf
						Else
							If Players(mp_I\PlayerID)\ReloadState = 0.0 Then
								If Players(mp_I\PlayerID)\ShootState = 0.0 Then
									If AnimTime(g\obj) > g\Anim_Shoot\x And AnimTime(g\obj) < g\Anim_Shoot\y-0.5 Then
										ChangeGunFrames(g,g\Anim_Shoot,False)
									ElseIf AnimTime(g\obj) > g\Anim_NoAmmo_Shoot\x And AnimTime(g\obj) < g\Anim_NoAmmo_Shoot\y-0.5 Then
										ChangeGunFrames(g,g\Anim_NoAmmo_Shoot,False)
									Else
										If Players(mp_I\PlayerID)\IsPlayerSprinting Then
											Players(mp_I\PlayerID)\PressMouse1 = False
											Players(mp_I\PlayerID)\PressReload = False
											
											If Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] = 0 Then
												If AnimTime(g\obj)<=(g\Anim_NoAmmo_Sprint_Transition\y-0.5) Lor AnimTime(g\obj)>(g\Anim_NoAmmo_Sprint_Cycle\y) Then
													ChangeGunFrames(g,g\Anim_NoAmmo_Sprint_Transition,False)
												Else
													ChangeGunFrames(g,g\Anim_NoAmmo_Sprint_Cycle,True)
												EndIf
											Else
												If AnimTime(g\obj)<=(g\Anim_Sprint_Transition\y-0.5) Lor AnimTime(g\obj)>(g\Anim_Sprint_Cycle\y) Then
													ChangeGunFrames(g,g\Anim_Sprint_Transition,False)
												Else
													ChangeGunFrames(g,g\Anim_Sprint_Cycle,True)
												EndIf
											EndIf
										Else
											If Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] = 0 Then
												If AnimTime(g\obj)>(g\Anim_NoAmmo_Sprint_Transition\x+0.5) And AnimTime(g\obj)<=g\Anim_NoAmmo_Sprint_Cycle\y Then
													ChangeGunFrames(g,g\Anim_NoAmmo_Sprint_Transition,False,True)
												Else
													SetAnimTime(g\obj,g\Frame_NoAmmo_Idle)
												EndIf
											Else
												If AnimTime(g\obj)>(g\Anim_Sprint_Transition\x+0.5) And AnimTime(g\obj)<=g\Anim_Sprint_Cycle\y Then
													ChangeGunFrames(g,g\Anim_Sprint_Transition,False,True)
												Else
													SetAnimTime(g\obj,g\Frame_Idle)
												EndIf
											EndIf
										EndIf
									EndIf
									
									If Players(mp_I\PlayerID)\PressMouse1 And Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot]=0 Then
										PlaySound_Strict g_I\ShootEmptySFX
									EndIf
								Else
									If Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] = 0 Then
										ChangeGunFrames(g,g\Anim_NoAmmo_Shoot,False)
										If Ceil(AnimTime(g\obj)) = g\Anim_NoAmmo_Shoot\x Then
											PlayGunSound("slideback2",1,1,False)
											If CameraShake <= (g\Knockback/2)-FPSfactor-0.05 Then
												PlayGunSound(g\name,g\MaxShootSounds,0,True)
												CameraShake = g\Knockback/2.0
												user_camera_pitch = user_camera_pitch - g\Knockback
												g_I\GunLightTimer = FPSfactor
												ShowEntity g\MuzzleFlash
												TurnEntity g\MuzzleFlash,0,0,Rnd(360)
												ScaleSprite g\MuzzleFlash,Rnd(0.025,0.03),Rnd(0.025,0.03)
											EndIf
											shooting = True
										EndIf
									Else
										ChangeGunFrames(g,g\Anim_Shoot,False)
										If Ceil(AnimTime(g\obj)) = g\Anim_Shoot\x Then
											PlayGunSound(g\name,g\MaxShootSounds,0,True)
											CameraShake = g\Knockback/2.0
											user_camera_pitch = user_camera_pitch - g\Knockback
											g_I\GunLightTimer = FPSfactor
											shooting = True
											ShowEntity g\MuzzleFlash
											TurnEntity g\MuzzleFlash,0,0,Rnd(360)
											ScaleSprite g\MuzzleFlash,Rnd(0.025,0.03),Rnd(0.025,0.03)
										EndIf
									EndIf
									If Players(mp_I\PlayerID)\ShootState >= g\Rate_Of_Fire-FPSfactor And Players(mp_I\PlayerID)\PressMouse1 Then
										SetAnimTime g\obj,g\Anim_Shoot\x
									EndIf
								EndIf
							Else
								If AnimTime(g\obj)>g\Anim_Reload\y Lor AnimTime(g\obj)<g\Anim_Reload\x Then
									SetAnimTime(g\obj,g\Anim_Reload\x)
								EndIf
								ChangeGunFrames(g,g\Anim_Reload,False)
								If prevFrame# < (g\Anim_Reload\x+1) And AnimTime(g\obj) >= (g\Anim_Reload\x+1) Then
									PlayGunSound(g\name+"\reload",g\MaxReloadSounds,1,False)
								ElseIf prevFrame# < (g\Anim_Reload\y-0.5) And AnimTime(g\obj) >= (g\Anim_Reload\y-0.5) Then
									Players(mp_I\PlayerID)\PressReload = False
								EndIf
								Players(mp_I\PlayerID)\IronSight = False
								g_I\IronSight = False
							EndIf
						EndIf
						
						If (Not Players(mp_I\PlayerID)\IsPlayerSprinting) And (Not shooting) Then
							g_I\GunAnimFLAG = False
						Else
							g_I\GunAnimFLAG = True
						EndIf
					EndIf
					;[End Block]
				Case GUNTYPE_SHOTGUN
					;[Block]
					If g\ID=Players(mp_I\PlayerID)\WeaponInSlot[Players(mp_I\PlayerID)\SelectedSlot] Then
						If MouseHit1 And (Not MenuOpen) And (Not ConsoleOpen) And (Not InTeamSelection()) Then
							Players(mp_I\PlayerID)\PressMouse1=True
							Players(mp_I\PlayerID)\PressReload=False
						EndIf
						
						If (Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot]=0 And Players(mp_I\PlayerID)\ShootState > 0.0) Lor Players(mp_I\PlayerID)\IsPlayerSprinting Then
							Players(mp_I\PlayerID)\PressMouse1=False
						EndIf
						
						If Players(mp_I\PlayerID)\ShootState = 0.0 And Players(mp_I\PlayerID)\PressMouse1 And Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] > 0 Then
							SetAnimTime(g\obj,g\Frame_Idle)
						EndIf
						
						If Players(mp_I\PlayerID)\DeployState < g\Deploy_Time Lor Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] = g\MaxCurrAmmo Lor Players(mp_I\PlayerID)\ReloadState = 0.0 Then
							Players(mp_I\PlayerID)\PressReload=False
						EndIf
						If Players(mp_I\PlayerID)\ReloadAmmo[Players(mp_I\PlayerID)\SelectedSlot]=0 Then
							Players(mp_I\PlayerID)\PressReload=False
						EndIf
						
						If KeyHit(KEY_RELOAD) And (Not MenuOpen) And (Not ConsoleOpen) Then
							If Players(mp_I\PlayerID)\ReloadState = 0.0 And Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] < g\MaxCurrAmmo And (Not Players(mp_I\PlayerID)\IronSight) Then
								Players(mp_I\PlayerID)\PressReload=True
							EndIf
						EndIf
						
						shooting = False
						If Players(mp_I\PlayerID)\DeployState < g\Deploy_Time Then
							ChangeGunFrames(g,g\Anim_Deploy,False)
							If prevFrame# < (g\Anim_Deploy\x+1) And AnimTime(g\obj) >= (g\Anim_Deploy\x+1) Then
								PlayGunSound(g\name+"\deploy",1,1,False)
							EndIf
						Else
							If Players(mp_I\PlayerID)\ReloadState = 0.0 Then
								If Players(mp_I\PlayerID)\ShootState = 0.0 Then
									If AnimTime(g\obj) > g\Anim_Shoot\x And AnimTime(g\obj) < g\Anim_Shoot\y-0.5 Then
										ChangeGunFrames(g,g\Anim_Shoot,False)
									ElseIf AnimTime(g\obj) >= g\Anim_Reload_Stop\x And AnimTime(g\obj) < (g\Anim_Reload_Stop\y-0.5) Then
										ChangeGunFrames(g,g\Anim_Reload_Stop,False)
										If AnimTime(g\obj) >= (g\Anim_Reload_Stop\y-0.5) Then
											SetAnimTime(g\obj,g\Frame_Idle)
											Players(mp_I\PlayerID)\PressReload = False
										EndIf
									Else
										If Players(mp_I\PlayerID)\IsPlayerSprinting Then
											Players(mp_I\PlayerID)\PressMouse1 = False
											Players(mp_I\PlayerID)\PressReload = False
											
											If AnimTime(g\obj)<=(g\Anim_Sprint_Transition\y-0.5) Lor AnimTime(g\obj)>(g\Anim_Sprint_Cycle\y) Then
												ChangeGunFrames(g,g\Anim_Sprint_Transition,False)
											Else
												ChangeGunFrames(g,g\Anim_Sprint_Cycle,True)
											EndIf
										Else
											If AnimTime(g\obj)>(g\Anim_Sprint_Transition\x+0.5) And AnimTime(g\obj)<=g\Anim_Sprint_Cycle\y Then
												ChangeGunFrames(g,g\Anim_Sprint_Transition,False,True)
											Else
												SetAnimTime(g\obj,g\Frame_Idle)
											EndIf
										EndIf
									EndIf
									
									If Players(mp_I\PlayerID)\PressMouse1 And Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot]=0 Then
										PlaySound_Strict g_I\ShootEmptySFX
									EndIf
								Else
									ChangeGunFrames(g,g\Anim_Shoot,False)
									If Ceil(AnimTime(g\obj)) = g\Anim_Shoot\x Then
										PlayGunSound(g\name,g\MaxShootSounds,0,True)
										CameraShake = g\Knockback/2.0
										user_camera_pitch = user_camera_pitch - g\Knockback
										g_I\GunLightTimer = FPSfactor
										shooting = True
										ShowEntity g\MuzzleFlash
										TurnEntity g\MuzzleFlash,0,0,Rnd(360)
										ScaleSprite g\MuzzleFlash,Rnd(0.025,0.03),Rnd(0.025,0.03)
									EndIf
									If Players(mp_I\PlayerID)\ShootState >= g\Rate_Of_Fire-FPSfactor And Players(mp_I\PlayerID)\PressMouse1 Then
										SetAnimTime g\obj,g\Anim_Shoot\x
									EndIf
								EndIf
							Else
								If AnimTime(g\obj)>g\Anim_Reload_Stop\y Lor AnimTime(g\obj)<g\Anim_Reload_Start\x Then
									SetAnimTime(g\obj,g\Anim_Reload_Start\x)
								EndIf
								If AnimTime(g\obj) >= g\Anim_Reload_Start\x And AnimTime(g\obj) < (g\Anim_Reload_Start\y-0.5) Then
									ChangeGunFrames(g,g\Anim_Reload_Start,False)
									If AnimTime(g\obj) >= (g\Anim_Reload_Start\y-0.5) Then
										SetAnimTime(g\obj,g\Anim_Reload\x)
										PlayGunSound(g\name+"\reload",g\MaxReloadSounds,1,False)
									EndIf
								ElseIf AnimTime(g\obj) >= g\Anim_Reload\x And AnimTime(g\obj) < (g\Anim_Reload\y-0.5) Then
									ChangeGunFrames(g,g\Anim_Reload,False)
									If AnimTime(g\obj) >= (g\Anim_Reload\y-0.5) Then
										If Players(mp_I\PlayerID)\Ammo[Players(mp_I\PlayerID)\SelectedSlot]<g\MaxCurrAmmo And Players(mp_I\PlayerID)\ReloadAmmo[Players(mp_I\PlayerID)\SelectedSlot]>0 Then
											SetAnimTime(g\obj,g\Anim_Reload\x)
											PlayGunSound(g\name+"\reload",g\MaxReloadSounds,1,False)
										Else
											SetAnimTime(g\obj,g\Anim_Reload_Stop\x)
											PlayGunSound(g\name+"\reload_stop",1,1,False)
										EndIf
									EndIf
								ElseIf AnimTime(g\obj) >= g\Anim_Reload_Stop\x And AnimTime(g\obj) < (g\Anim_Reload_Stop\y-0.5) Then
									ChangeGunFrames(g,g\Anim_Reload_Stop,False)
									If AnimTime(g\obj) >= (g\Anim_Reload_Stop\y-0.5) Then
										SetAnimTime(g\obj,g\Frame_Idle)
										Players(mp_I\PlayerID)\PressReload = False
									EndIf
								EndIf
								Players(mp_I\PlayerID)\IronSight = False
								g_I\IronSight = False
							EndIf
						EndIf
						
						If (Not Players(mp_I\PlayerID)\IsPlayerSprinting) And (Not shooting) Then
							g_I\GunAnimFLAG = False
						Else
							g_I\GunAnimFLAG = True
						EndIf
					EndIf
					;[End Block]
				Case GUNTYPE_MELEE
					;[Block]
					If g\ID=Players(mp_I\PlayerID)\WeaponInSlot[Players(mp_I\PlayerID)\SelectedSlot] Then
						If MouseDown1 And (Not MenuOpen) And (Not ConsoleOpen) And (Not InTeamSelection()) Then
							Players(mp_I\PlayerID)\PressMouse1=True
						EndIf
						
						If Players(mp_I\PlayerID)\ShootState > 0.0 Lor Players(mp_I\PlayerID)\IsPlayerSprinting Then
							Players(mp_I\PlayerID)\PressMouse1=False
						EndIf
						
						If Players(mp_I\PlayerID)\ShootState = 0.0 And Players(mp_I\PlayerID)\PressMouse1 Then
							SetAnimTime(g\obj,g\Frame_Idle)
						EndIf
						
						shooting = False
						If Players(mp_I\PlayerID)\DeployState < g\Deploy_Time Then
							ChangeGunFrames(g,g\Anim_Deploy,False)
							If prevFrame# < (g\Anim_Deploy\x+1) And AnimTime(g\obj) >= (g\Anim_Deploy\x+1) Then
								PlayGunSound(g\name+"\deploy",1,1,False)
							EndIf
						Else
							If Players(mp_I\PlayerID)\ShootState = 0.0 Then
								If AnimTime(g\obj) > g\Anim_Shoot\x And AnimTime(g\obj) < g\Anim_Shoot\y-0.5 Then
									ChangeGunFrames(g,g\Anim_Shoot,False)
								Else
									If Players(mp_I\PlayerID)\IsPlayerSprinting Then
										Players(mp_I\PlayerID)\PressMouse1 = False
										Players(mp_I\PlayerID)\PressReload = False
										
										If AnimTime(g\obj)<=(g\Anim_Sprint_Transition\y-0.5) Lor AnimTime(g\obj)>(g\Anim_Sprint_Cycle\y) Then
											ChangeGunFrames(g,g\Anim_Sprint_Transition,False)
										Else
											ChangeGunFrames(g,g\Anim_Sprint_Cycle,True)
										EndIf
									Else
										If AnimTime(g\obj)>(g\Anim_Sprint_Transition\x+0.5) And AnimTime(g\obj)<=g\Anim_Sprint_Cycle\y Then
											ChangeGunFrames(g,g\Anim_Sprint_Transition,False,True)
										Else
											SetAnimTime(g\obj,g\Frame_Idle)
										EndIf
									EndIf
								EndIf
								Players(mp_I\PlayerID)\ReloadState = 0.0
							Else
								ChangeGunFrames(g,g\Anim_Shoot,False)
								If Players(mp_I\PlayerID)\ShootState >= g\ShootDelay And Players(mp_I\PlayerID)\ReloadState = 0.0 Then
									If EntityPick(Players(mp_I\PlayerID)\GunPivot,g\Range)
										PlayGunSound(g\name+"\slice",g\MaxShootSounds,1,True)
									Else
										PlayGunSound(g\name+"\miss",1,1,True)
									EndIf
									Players(mp_I\PlayerID)\ReloadState = 1.0
								EndIf
								If Players(mp_I\PlayerID)\ShootState >= g\Rate_Of_Fire-FPSfactor And Players(mp_I\PlayerID)\PressMouse1 Then
									SetAnimTime g\obj,g\Anim_Shoot\x
									Players(mp_I\PlayerID)\ReloadState = 0.0
								EndIf
							EndIf
						EndIf
						
						If (Not Players(mp_I\PlayerID)\IsPlayerSprinting) And (Not shooting) Then
							g_I\GunAnimFLAG = False
						Else
							g_I\GunAnimFLAG = True
						EndIf
					EndIf
					;[End Block]
			End Select
		Next
	EndIf
	
End Function

Function PlayGunSoundsMP()
	CatchErrors("PlayGunSoundsMP")
	
;	Local mp_I.MultiplayerInstance = First MultiplayerInstance
;	Local g.Guns
;	
;	Local i
;	
;	For i = 0 To (mp_I\MaxPlayers-1)
;		If Players(i)<>Null Then
;			If i<>mp_I\PlayerID Then
;				If Players(i)\GunModelMuzzleFlash<>0 Then
;					HideEntity Players(i)\GunModelMuzzleFlash
;				EndIf
;				For g = Each Guns
;					If g\ID = Players(i)\WeaponInSlot[Players(i)\SelectedSlot] Then
;						If Players(i)\PressMouse1 And Players(i)\Ammo[Players(mp_I\PlayerID)\SelectedSlot] > 0 Then
;							If Players(i)\ShootState > 0.0 And (Players(i)\PrevShootState = 0.0 Lor Players(i)\PrevShootState > Players(i)\ShootState) Then
;								Players(i)\GunSFXChannel = PlaySound2(g\ShootSounds[Rand(0,g\MaxShootSounds-1)],Camera,Players(i)\Collider,20)
;								If mp_I\PlayState = GAME_CLIENT Then
;									Players(i)\PressMouse1 = False
;								EndIf
;								ShowEntity Players(i)\GunModelMuzzleFlash
;								ScaleSprite Players(i)\GunModelMuzzleFlash,Rnd(0.125,0.15),Rnd(0.125,0.15)
;								TurnEntity Players(i)\GunModelMuzzleFlash,0,0,Rnd(360)
;							EndIf
;							Exit
;						EndIf
;					EndIf
;				Next
;				
;				UpdateSoundOrigin(Players(i)\GunSFXChannel,Camera,Players(i)\Collider,20)
;			EndIf
;		EndIf
;	Next
	
	CatchErrors("Uncaught (PlayGunSoundsMP)")
End Function

Function ShootGunMP(playerID%,Accuracy#,DamageOnEntity%,Range#=0.0)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local temp,n.NPCs,p.Particles,j,de.Decals,ent_pick%
	Local i%
	Local prevHP#
	
	For n.NPCs = Each NPCs
		ShowNPCHitBoxes(n)
	Next
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			If i<>playerID Then
				ShowPlayerHitBoxes(i)
			EndIf
		EndIf
	Next
	EntityPickMode GetChild(mp_I\Map\obj,RMESH_INVISBLE),0
	If mp_I\MapInList\ChunkEnd > 0 Then
		For i = mp_I\MapInList\ChunkStart To mp_I\MapInList\ChunkEnd
			EntityPickMode GetChild(mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart],RMESH_INVISBLE),0
		Next
	EndIf
	Local pitch# = EntityPitch(Players(mp_I\PlayerID)\GunPivot)
	Local yaw# = EntityYaw(Players(mp_I\PlayerID)\GunPivot)
	TurnEntity Players(playerID)\GunPivot,Rnd(-Accuracy,Accuracy)/(1.0+(3.0*Players(playerID)\IronSight)),Rnd(-Accuracy,Accuracy)/(1.0+(3.0*Players(playerID)\IronSight)),0
	If Range<=0.0 Then
		EntityPick Players(playerID)\GunPivot,20.0
	Else
		EntityPick Players(playerID)\GunPivot,Range
	EndIf
	temp = 0
	ent_pick% = PickedEntity()
	If ent_pick%<>0 Then
		temp = 1
		For n.NPCs = Each NPCs
			For j = 0 To 24
				If ent_pick% = n\HitBox1[j] ;Head has been shot, instant death (when a NPC has 100 HP, otherwise not)
					n\HP = n\HP - 100
					n\GotHit = 100
					temp = 2
					Exit
				EndIf
				If ent_pick% = n\HitBox2[j] ;Body has been shot, doing damage with g\DamageOnEntity
					n\HP = n\HP - DamageOnEntity
					n\GotHit = DamageOnEntity
					temp = 2
					Exit
				EndIf
				If ent_pick% = n\HitBox3[j] ;Arms or legs have been shot, doing half as much damage as with the body
					n\HP = n\HP - (DamageOnEntity/2)
					n\GotHit = (DamageOnEntity/2)
					temp = 2
					Exit
				EndIf
			Next
		Next
		For i = 0 To (mp_I\MaxPlayers-1)
			If Players(i)<>Null Then
				prevHP = Players(i)\CurrHP
				For j = 0 To 24
					If ent_pick% = Players(i)\HitBox1[j] Then ;Head has been shot, instant death (when a NPC has 100 HP, otherwise not)
						If Players(i)\Team<>Players(playerID)\Team Then
							DamagePlayer(i, 100, 100)
							temp = 2
						Else
							temp = 0
						EndIf
						Exit
					ElseIf ent_pick% = Players(i)\HitBox2[j] Then ;Body has been shot, doing damage with g\DamageOnEntity
						If Players(i)\Team<>Players(playerID)\Team Then
							DamagePlayer(i, DamageOnEntity/2, DamageOnEntity)
							temp = 2
						Else
							temp = 0
						EndIf
						Exit
					ElseIf ent_pick% = Players(i)\HitBox3[j] Then ;Arms or legs have been shot, doing half as much damage as with the body
						If Players(i)\Team<>Players(playerID)\Team Then
							DamagePlayer(i, DamageOnEntity/4, DamageOnEntity/2)
							temp = 2
						Else
							temp = 0
						EndIf
						Exit
					EndIf
				Next
				If Players(i)\CurrHP <= 0 And prevHP > 0 Then
					Players(playerID)\Kills = Players(playerID)\Kills + 1
				EndIf
			EndIf
		Next
	EndIf
	Local pmp.ParticleMP
	If temp = 2 Then
		pmp = CreateParticleMP(PARTICLEMP_SHOT,PickedX(),PickedY(),PickedZ(),0,0,0,0,0)
	ElseIf temp = 1 Then
		pmp = CreateParticleMP(PARTICLEMP_WALL,PickedX(),PickedY(),PickedZ(),EntityPitch(Players(playerID)\GunPivot),EntityYaw(Players(playerID)\GunPivot),-PickedNX(),-PickedNY(),-PickedNZ())
	EndIf
	
	If (Not Players(playerID)\IronSight) Then
		RotateEntity Players(mp_I\PlayerID)\GunPivot,pitch,yaw,0
	EndIf
	EntityPickMode GetChild(mp_I\Map\obj,RMESH_INVISBLE),2
	If mp_I\MapInList\ChunkEnd > 0 Then
		For i = mp_I\MapInList\ChunkStart To mp_I\MapInList\ChunkEnd
			EntityPickMode GetChild(mp_I\Map\Chunks[i-mp_I\MapInList\ChunkStart],RMESH_INVISBLE),2
		Next
	EndIf
	For n.NPCs = Each NPCs
		HideNPCHitBoxes(n)
	Next
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			HidePlayerHitBoxes(i)
		EndIf
	Next
	
End Function

Function DamagePlayer(playerid%,hpdamage%,kevlardamage%,kevlarprotectionrate#=5.0)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	If mp_I\PlayState = GAME_CLIENT Then Return
	
	If Players(playerid)\CurrKevlar>0 Then
		Players(playerid)\CurrKevlar = Max(Players(playerid)\CurrKevlar-(kevlardamage*(1+(mp_I\Gamemode\Difficulty*0.5))),0)
		Players(playerid)\CurrHP = Max(Players(playerid)\CurrHP-((hpdamage/kevlarprotectionrate#)*(1+(mp_I\Gamemode\Difficulty*0.5))),0)
	Else
		Players(playerid)\CurrHP = Max(Players(playerid)\CurrHP-hpdamage*(1+(mp_I\Gamemode\Difficulty*0.5)),0)
	EndIf
	
End Function

Function AnimateGunsServer()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local g_I.GunInstance = First GunInstance
	Local mpl.MainPlayer = First MainPlayer
	
	If (Not g_I\GunAnimFLAG) And Players(mp_I\PlayerID)\CurrSpeed=0.0 And (Not Players(mp_I\PlayerID)\IsPlayerSprinting) And (Not Players(mp_I\PlayerID)\IronSight)
		If GunPivot_YSide%=0
			If GunPivot_Y# > -0.005
				GunPivot_Y# = GunPivot_Y# - (0.00005*FPSfactor)
			Else
				GunPivot_Y# = -0.005
				GunPivot_YSide% = 1
			EndIf
		Else
			If GunPivot_Y# < 0.0
				GunPivot_Y# = GunPivot_Y# + (0.00005*FPSfactor)
			Else
				GunPivot_Y# = 0.0
				GunPivot_YSide% = 0
			EndIf
		EndIf
		
		If GunPivot_X# < 0.0
			GunPivot_X# = Min(GunPivot_X#+(0.0001*FPSfactor),0.0)
		ElseIf GunPivot_X# > 0.0
			GunPivot_X# = Max(GunPivot_X#-(0.0001*FPSfactor),0.0)
		EndIf
	ElseIf (Not g_I\GunAnimFLAG) And Players(mp_I\PlayerID)\CurrSpeed<>0.0 And (Not Players(mp_I\PlayerID)\IsPlayerSprinting%) And (Not Players(mp_I\PlayerID)\IronSight)
        If GunPivot_XSide%=0
            If GunPivot_X# > -0.0025
                GunPivot_X# = GunPivot_X# - (0.000075 / (1.0 + (Players(mp_I\PlayerID)\Crouch)) * FPSfactor)
                If GunPivot_X# > -0.00125
                    GunPivot_Y# = Min(GunPivot_Y#+(0.000125 / (1.0 + (Players(mp_I\PlayerID)\Crouch)) * FPSfactor),0.001)
                Else
                    GunPivot_Y# = Max(GunPivot_Y#-(0.000125 / (1.0 + (Players(mp_I\PlayerID)\Crouch)) * FPSfactor),0.0)
                EndIf
            Else
                GunPivot_X# = -0.0025
                GunPivot_Y# = 0.0
                GunPivot_XSide% = 1
            EndIf
        Else
            If GunPivot_X# < 0.0
                GunPivot_X# = GunPivot_X# + (0.000075 / (1.0 + (Players(mp_I\PlayerID)\Crouch)) * FPSfactor)
                If GunPivot_X# < -0.00125
                    GunPivot_Y# = Min(GunPivot_Y#+(0.000125 / (1.0 + (Players(mp_I\PlayerID)\Crouch)) * FPSfactor),0.001)
                Else
                    GunPivot_Y# = Max(GunPivot_Y#-(0.000125 / (1.0 + (Players(mp_I\PlayerID)\Crouch)) * FPSfactor),0.0)
                EndIf
            Else
                GunPivot_X# = 0.0
                GunPivot_Y# = 0.0
                GunPivot_XSide% = 0
            EndIf
        EndIf
    Else
		If GunPivot_Y# < 0.0
			GunPivot_Y# = Max(GunPivot_Y#+(0.0001*FPSfactor),0.0)
		Else
			GunPivot_Y# = 0.0
		EndIf
		
		If GunPivot_X# < 0.0
			GunPivot_X# = Min(GunPivot_X#+(0.0001*FPSfactor),0.0)
		ElseIf GunPivot_X# > 0.0
			GunPivot_X# = Max(GunPivot_X#-(0.0001*FPSfactor),0.0)
		EndIf
	EndIf
	
	PositionEntity g_I\GunPivot,EntityX(mpl\CameraPivot), EntityY(mpl\CameraPivot)+GunPivot_Y#, EntityZ(mpl\CameraPivot)
	MoveEntity g_I\GunPivot,GunPivot_X#,0,0
	
End Function

;Unused
;[Block]
;Function PlaceSpray()
;	Local n.NPCs
;	
;	If KeyHit(KB\SprayKey) Then
;		For n = Each NPCs
;			HideNPCHitBoxes(n)
;		Next
;		EntityPick(Camera,2.0)
;		If PickedEntity()<>0 Then
;			PositionEntity Players(mp_I\PlayerID)\SpraySprite,PickedX(),PickedY(),PickedZ()
;			AlignToVector Players(mp_I\PlayerID)\SpraySprite,-PickedNX(),-PickedNY(),-PickedNZ(),3
;			MoveEntity Players(mp_I\PlayerID)\SpraySprite,0,0,-0.001
;			RotateEntity Players(mp_I\PlayerID)\SpraySprite,EntityPitch(Players(mp_I\PlayerID)\SpraySprite,True),EntityYaw(Players(mp_I\PlayerID)\SpraySprite,True),0,True
;		EndIf
;		For n = Each NPCs
;			ShowNPCHitBoxes(n)
;		Next
;	EndIf
;	
;End Function
;[End Block]

Function GetClosestPlayerID(n.NPCs)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local i,j
	
	Local dist# = 10000.0
	Local dist2# = 0.0
	Local smallestdist% = 0
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			If Players(i)\CurrHP > 0 And Players(i)\Team > Team_Spectator Then
				dist2# = EntityDistanceSquared(n\Collider,Players(i)\Collider)
				If dist2 < dist Then
					dist = dist2
					smallestdist = i
				EndIf
			EndIf
		EndIf
	Next
	
	Return smallestdist
	
End Function

Function NullMPGame(nomenuload%=False,playbuttonsfx%=True)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local gv.GlobalVariables = First GlobalVariables
	CatchErrors("Uncaught (NullMPGame)")
	Local i%, x%, y%, lvl
	Local mfl.MapForList
	
	SaveMSG = ""
	Disconnect()
	RemoveServer()
	
	;Reinit multiplayer instance
	Local PlayerName$ = mp_I\PlayerName
	Local ServerPort% = mp_I\ServerPort
	Local MaxPlayers% = mp_I\MaxPlayers
	Local TimeOut% = mp_I\TimeOut
	Local ConnectAddr$ = mp_I\ConnectAddress
	Local ConnectPort% = mp_I\ConnectPort
	Local LocalServer% = mp_I\LocalServer
	Local ServerName$ = mp_I\ServerName
	;Local mgm.MultiplayerGameMode = mp_I\Gamemode
	Local mgm.MultiplayerGameMode
	For mgm = Each MultiplayerGameMode
		For i = 0 To MaxGamemodeImages-1
			If mgm\img[i] <> 0 Then
				FreeImage mgm\img[i]
			EndIf
		Next
	Next
	Delete Each MultiplayerGameMode
	mp_I\PlayerName = PlayerName
	mp_I\ServerPort = ServerPort
	mp_I\MaxPlayers = MaxPlayers
	mp_I\TimeOut = TimeOut
	;mp_I\EnableSprays = False
	mp_I\ConnectAddress = ConnectAddr
	mp_I\ConnectPort = ConnectPort
	mp_I\LocalServer = LocalServer
	mp_I\ServerName = ServerName
	mp_I\SelectedServer = ""
	Local SelMap$ = GetINIString(gv\OptionFile,"server","map","")
	If SelMap<>"" Then
		;Get the selected map, depending on what the player has selected
		For mfl = Each MapForList
			If mfl\Name = SelMap Then
				mp_I\MapInList = mfl
				Exit
			EndIf
		Next
	Else
		;No name/entry found, just use the first map available in the list
		mp_I\MapInList = First MapForList
	EndIf
	;Reinitialize multiplayer gamemodes
	LoadMPGameModes()
	
	LoadMultiplayerMenuResources()
	
	KillSounds()
	If playbuttonsfx Then PlaySound_Strict ButtonSFX
	
	DeleteCustomSoundEmitters()
	DeleteNewElevators()
	
	;ClearTextureCache
	DeleteTextureEntriesFromCache(2)
	
	UnableToMove% = False
	
	QuickLoadPercent = -1
	QuickLoadPercent_DisplayTimer# = 0
	QuickLoad_CurrEvent = Null
	
	HideDistance# = 15.0
	
	DropSpeed = 0
	Shake = 0
	CurrSpeed = 0
	
	DeathTimer=0
	
	HeartBeatVolume = 0
	
	StaminaEffect = 1.0
	StaminaEffectTimer = 0
	
	CameraShake = 0
	Shake = 0
	LightFlash = 0
	
	WireframeState = 0
	WireFrame 0
	
	ForceMove = 0.0
	ForceAngle = 0.0	
	Playable = True
	
	For i = 0 To MAXACHIEVEMENTS-1
		Achievements(i)=0
	Next
	
	ConsoleInput = ""
	ConsoleOpen = False
	
	ShouldPlay = 0
	
	Stamina = 100
	BlurTimer = 0
	
	Msg = ""
	MsgTimer = 0
	
	For i = 0 To MaxItemAmount - 1
		Inventory(i) = Null
	Next
	SelectedItem = Null
	
	;Some of that stuff should be added in the initializing stage of the multiplayer!
	Delete Each Doors
	Delete Each LightTemplates
	Delete Each Materials
	Delete Each WayPoints
	Delete Each TempWayPoints
	Delete Each ItemTemplates
	Delete Each Items
	Delete Each Props
	Delete Each Decals
	Delete Each NPCs
	Delete Each Emitters
	Delete Each Particles
	Delete Each ConsoleMsg
	Delete Each ItemSpawner
	Delete Each EnemySpawner
	Delete Each PlayerSpawner
	Delete Each Player
	Delete Each MultiplayerMap
	Delete Each ChatMSG
	Delete Each TempFluLight
	
	OptionsMenu% = -1
	QuitMSG% = -1
	AchievementsMenu% = -1
	
	MusicVolume# = PrevMusicVolume
	opt\SFXVolume# = PrevSFXVolume
	DeafPlayer% = False
	DeafTimer# = 0.0
	
	DeleteModStuff()
	
	NTF_GameModeFlag = 0
	
	Delete Each AchievementMsg
	CurrAchvMSGID = 0
	
	LastItemID% = 0
	
	Delete Each DoorInstance
	Delete Each GunInstance
	DeleteVectors2D()
	DeleteVectors3D()
	
	ClearWorld
	ReloadAAFont()
	Camera = 0
	ark_blur_cam = 0
	InitFastResize()
	
	Delete Each Menu3DInstance
	
	MainMenuOpen = True
	InitConsole(2)
	Load3DMenu()
	
	DeleteMenuGadgets()
	
	CatchErrors("NullMPGame")
End Function

Function UpdateGamemodeMP()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local it.Items, its.ItemSpawner, ps.PlayerSpawner
	Local playerMultiplier# = 1.0
	Local maxPeopleOnTeamMTF%, maxPeopleOnTeamCI%, peopleOnTeamMTFDead%, peopleOnTeamCIDead%
	Local roundEnd%
	Local i
	
	;Add a certain multiplier that makes more NPCs to spawn per wave when the player count increases
	If mp_I\PlayerCount >= 2 Then
		playerMultiplier = 1.5
		For i = 2 To mp_I\PlayerCount-1
			playerMultiplier = playerMultiplier + 0.25
		Next
	EndIf
	
	Select mp_I\Gamemode\ID
		Case Gamemode_Waves
			;[Block]
			Select mp_I\Gamemode\Phase
				Case Waves_StartServer
					mp_I\Gamemode\PhaseTimer = 70*30
					mp_I\Gamemode\Phase = Waves_StartGame
				Case Waves_StartGame
					If mp_I\Gamemode\PhaseTimer <= 0 Then
						mp_I\Gamemode\Phase = Waves_StartGame+1
						mp_I\Gamemode\PhaseTimer = 70*5
						mp_I\Gamemode\EnemyCount = Int(20*playerMultiplier*(1+(mp_I\Gamemode\Difficulty*0.25)))
						mp_I\Gamemode\PrevEnemyCount = mp_I\Gamemode\EnemyCount
						PlaySound_Strict LoadTempSound("SFX\General\WaveStart.ogg")
					Else
						mp_I\Gamemode\PhaseTimer = mp_I\Gamemode\PhaseTimer - FPSfactor
					EndIf
				Case Waves_End
					If mp_I\Gamemode\PhaseTimer <= 0 Then
						RuntimeError "Memory Access FUCK" ;<--- currently as a placeholder, too lazy to complete it rn, as the entire joining / map change system will be built from scratch, at least the majority - ENDSHN
					Else
						mp_I\Gamemode\PhaseTimer = mp_I\Gamemode\PhaseTimer - FPSfactor
					EndIf
				Default
					If (mp_I\Gamemode\Phase Mod 2) = 0
						If mp_I\Gamemode\EnemyCount = 0 Then
							mp_I\Gamemode\PhaseTimer = 70*60-(15*mp_I\Gamemode\Difficulty)
							mp_I\Gamemode\Phase = mp_I\Gamemode\Phase + 1
							For it = Each Items
								RemoveItem(it)
							Next
							For its = Each ItemSpawner
								its\time = 0.0
								its\picked = True
							Next
							For ps = Each PlayerSpawner
								ps\hasPlayerSpawned = False
							Next
							RespawnPlayers()
						EndIf
						If mp_I\Gamemode\PhaseTimer > 0 Then
							mp_I\Gamemode\PhaseTimer = mp_I\Gamemode\PhaseTimer - FPSfactor
						EndIf
					Else
						If mp_I\Gamemode\PhaseTimer <= 0 Then
							mp_I\Gamemode\Phase = mp_I\Gamemode\Phase + 1
							mp_I\Gamemode\PhaseTimer = 70*5
							mp_I\Gamemode\EnemyCount = Int(mp_I\Gamemode\PrevEnemyCount+(Float(mp_I\Gamemode\Phase/2))*playerMultiplier*(1+(mp_I\Gamemode\Difficulty*0.25)))
							mp_I\Gamemode\PrevEnemyCount = mp_I\Gamemode\EnemyCount
							PlaySound_Strict LoadTempSound("SFX\General\WaveStart.ogg")
						Else
							mp_I\Gamemode\PhaseTimer = mp_I\Gamemode\PhaseTimer - FPSfactor
						EndIf
					EndIf
			End Select
			;[End Block]
		Case Gamemode_Deathmatch
			;[Block]
			Select mp_I\Gamemode\Phase
				Case Deathmatch_GameStart
					For ps = Each PlayerSpawner
						ps\hasPlayerSpawned = False
					Next
					
					For i = 0 To (mp_I\MaxPlayers-1)
						If Players(i)<>Null Then
							If Players(i)\Team > Team_Spectator Then
								RespawnPlayer(i)
							EndIf
						EndIf
					Next
					
					mp_I\Gamemode\PhaseTimer = 70*5
					mp_I\Gamemode\Phase = Deathmatch_Game
				Case Deathmatch_MTFLost, Deathmatch_CILost
					If mp_I\Gamemode\PhaseTimer > 0 Then
						mp_I\Gamemode\PhaseTimer = mp_I\Gamemode\PhaseTimer - FPSfactor
					Else
						mp_I\Gamemode\Phase = Deathmatch_GameStart
					EndIf
				Case Deathmatch_Game
					If mp_I\Gamemode\PhaseTimer > 0 Then
						mp_I\Gamemode\PhaseTimer = mp_I\Gamemode\PhaseTimer - FPSfactor
					Else
						mp_I\Gamemode\PhaseTimer = 0.0
						;For i = 0 To (mp_I\MaxPlayers-1)
						;	If Players(i)<>Null Then
						;		Players(i)\Disable = False
						;	EndIf
						;Next
						maxPeopleOnTeamMTF = 0
						maxPeopleOnTeamCI = 0
						peopleOnTeamMTFDead = 0
						peopleOnTeamCIDead = 0
						For i = 0 To (mp_I\MaxPlayers-1)
							If Players(i)<>Null Then
								If Players(i)\Team = Team_MTF Then
									maxPeopleOnTeamMTF = maxPeopleOnTeamMTF + 1
									If Players(i)\CurrHP <= 0 Then
										peopleOnTeamMTFDead = peopleOnTeamMTFDead + 1
									EndIf
								ElseIf Players(i)\Team = Team_CI Then
									maxPeopleOnTeamCI = maxPeopleOnTeamCI + 1
									If Players(i)\CurrHP <= 0 Then
										peopleOnTeamCIDead = peopleOnTeamCIDead + 1
									EndIf
								EndIf
							EndIf
						Next
						
						roundEnd% = False
						
						;All MTF units are dead
						If maxPeopleOnTeamMTF > 0 And peopleOnTeamMTFDead = maxPeopleOnTeamMTF Then
							roundEnd = True
							mp_I\Gamemode\Phase = Deathmatch_MTFLost
						EndIf
						
						;All CI members are dead
						If maxPeopleOnTeamCI > 0 And peopleOnTeamCIDead = maxPeopleOnTeamCI Then
							roundEnd = True
							mp_I\Gamemode\Phase = Deathmatch_CILost
						EndIf
						
						If roundEnd Then
							mp_I\Gamemode\PhaseTimer = 70*5
						EndIf
					EndIf
			End Select
			;[End Block]
	End Select
	
End Function

Function InTeamSelection()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	If mp_I\Gamemode\ID = Gamemode_Deathmatch Then
		If Players(mp_I\PlayerID)\Team = Team_Unknown Then
			Return True
		EndIf
	EndIf
	Return False
	
End Function

Function FindPathMP(n.NPCs, x#, y#, z#)
	
	DebugLog "findpathMP: "+n\NPCtype
	
	Local temp%, dist#, dist2#
	Local xtemp#, ytemp#, ztemp#
	
	Local w.WayPoints, StartPoint.WayPoints, EndPoint.WayPoints   
	
	Local StartX% = Floor(EntityX(n\Collider,True) / 8.0 + 0.5), StartZ% = Floor(EntityZ(n\Collider,True) / 8.0 + 0.5)
	
	Local EndX% = Floor(x / 8.0 + 0.5), EndZ% = Floor(z / 8.0 + 0.5)
	
	Local CurrX, CurrZ
	
	Local i,smallest.WayPoints,gtemp#
	
   ;pathstatus = 0, route hasn't been searched for yet
   ;pathstatus = 1, route found
   ;pathstatus = 2, route not found (target unreachable)
	
	For w.WayPoints = Each WayPoints
		w\state = 0
		w\Fcost = 0
		w\Gcost = 0
		w\Hcost = 0
	Next
	
	n\PathStatus = 0
	n\PathLocation = 0
	For i = 0 To 19
		n\Path[i] = Null
	Next
	
	Local pvt = CreatePivot()
	PositionEntity(pvt, x,y,z, True)   
	
	temp = CreatePivot()
	PositionEntity(temp, EntityX(n\Collider,True), EntityY(n\Collider,True)+0.15, EntityZ(n\Collider,True))
	
	dist = 350.0
	For w.WayPoints = Each WayPoints
;		xtemp = EntityX(w\obj,True)-EntityX(temp,True)
;		ztemp = EntityZ(w\obj,True)-EntityZ(temp,True)
;		ytemp = EntityY(w\obj,True)-EntityY(temp,True)
;		dist2# = (xtemp*xtemp)+(ytemp*ytemp)+(ztemp*ztemp)
		dist2# = EntityDistanceSquared(w\obj,temp)
		If dist2 < dist Then 
			;prefer waypoints that are visible
			If dist2 < dist Then 
				dist = dist2
				StartPoint = w
			EndIf
		EndIf
	Next
	DebugLog "DIST: "+dist
	
	FreeEntity temp
	
	If StartPoint = Null Then Return 2
	StartPoint\state = 1      
	
	EndPoint = Null
	dist# = 400.0
	For w.WayPoints = Each WayPoints
;		xtemp = EntityX(pvt,True)-EntityX(w\obj,True)
;		ztemp = EntityZ(pvt,True)-EntityZ(w\obj,True)
;		ytemp = EntityY(pvt,True)-EntityY(w\obj,True)
;		dist2# = (xtemp*xtemp)+(ytemp*ytemp)+(ztemp*ztemp)
		dist2# = EntityDistanceSquared(pvt,w\obj)
		
		If dist2 < dist Then
			dist = dist2
			EndPoint = w
		EndIf
	Next
	
	FreeEntity pvt
	
	If EndPoint = StartPoint Then
		If dist < 0.4 Then
			Return 0
		Else
			n\Path[0]=EndPoint
			Return 1               
		EndIf
	EndIf
	If EndPoint = Null Then Return 2
	
	Repeat
		
		temp% = False
		smallest.WayPoints = Null
		dist# = 10000.0
		For w.WayPoints = Each WayPoints
			If w\state = 1 Then
                temp = True
                If (w\Fcost) < dist Then
					dist = w\Fcost
					smallest = w
                EndIf
			EndIf
		Next
		
		If smallest <> Null Then
			
			w = smallest
			w\state = 2
			
			For i = 0 To 4
                If w\connected[i]<>Null Then
					If w\connected[i]\state < 2 Then
						
						If w\connected[i]\state=1 Then ;open list
							gtemp# = w\Gcost+w\dist[i]
							If gtemp < w\connected[i]\Gcost Then ; -> overwrite
								w\connected[i]\Gcost = gtemp
								w\connected[i]\Fcost = w\connected[i]\Gcost + w\connected[i]\Hcost
								w\connected[i]\parent = w
							EndIf
						Else
							w\connected[i]\Hcost# = Abs(EntityX(w\connected[i]\obj,True)-EntityX(EndPoint\obj,True))+Abs(EntityZ(w\connected[i]\obj,True)-EntityZ(EndPoint\obj,True))
							gtemp# = w\Gcost+w\dist[i]
							w\connected[i]\Gcost = gtemp
							w\connected[i]\Fcost = w\Gcost+w\Hcost
							w\connected[i]\parent = w
							w\connected[i]\state=1
						EndIf            
					EndIf
					
                EndIf
			Next
		Else ;open
			If EndPoint\state > 0 Then
                StartPoint\parent = Null
                EndPoint\state = 2
                Exit
			EndIf
		EndIf
		
		If EndPoint\state > 0 Then
			StartPoint\parent = Null
			EndPoint\state = 2
			Exit
		EndIf
		
	Until temp = False
	
	If EndPoint\state > 0 Then
		
		Local currpoint.WayPoints = EndPoint
		Local twentiethpoint.WayPoints = EndPoint
		
		Local length = 0
		Repeat
			length = length +1
			currpoint = currpoint\parent
			If length>20 Then
                twentiethpoint = twentiethpoint\parent
			EndIf
		Until currpoint = Null
		
		currpoint.WayPoints = EndPoint
		While twentiethpoint<>Null
			length=Min(length-1,19)
			twentiethpoint = twentiethpoint\parent
			n\Path[length] = twentiethpoint
		Wend
		
		Return 1
	Else
		DebugLog "FUNCTION FindPathMP() - no route found"
		Return 2 
	EndIf
	
End Function

Function UpdateChunksMP()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local i,j
	Local mpl.MainPlayer = First MainPlayer
	
	If mp_I\Map\CurrChunk = 0 Then
		EntityAlpha GetChild(mp_I\Map\obj,RMESH_BSP),1.0
	Else
		EntityAlpha GetChild(mp_I\Map\obj,RMESH_BSP),0.0
	EndIf
	For i = 0 To MaxChunks
		If mp_I\Map\Chunks[i]=0 Then
			Exit
		Else
			If mp_I\Map\CurrChunk = (i+1) Then
				EntityAlpha GetChild(mp_I\Map\Chunks[i],RMESH_BSP),1.0
			Else
				EntityAlpha GetChild(mp_I\Map\Chunks[i],RMESH_BSP),0.0
			EndIf
		EndIf
	Next
	
	For i = 0 To MaxTriggers
		If mp_I\Map\Triggers[i]=0 Then
			Exit
		Else
			If Int(mp_I\MapInList\TriggerAreas[i]\x)-1 = mp_I\Map\CurrChunk Then
				If EntityDistanceSquared(mp_I\Map\Triggers[i],mpl\CameraPivot)<PowTwo(7.0) Then
					If EntityInView(mp_I\Map\Triggers[i],Camera) Then
						If Int(mp_I\MapInList\TriggerAreas[i]\y)-1=0 Then
							EntityAlpha GetChild(mp_I\Map\obj,RMESH_BSP),1.0
						Else
							EntityAlpha GetChild(mp_I\Map\Chunks[Int(mp_I\MapInList\TriggerAreas[i]\y)-2],RMESH_BSP),1.0
						EndIf
						
						If CheckSpecificTrigger(mpl\CameraPivot,mp_I\Map\Triggers[i]) Then
							If EntityDistanceSquared(mp_I\Map\TriggerPoint1[i],mpl\CameraPivot)>EntityDistanceSquared(mp_I\Map\TriggerPoint2[i],mpl\CameraPivot) Then
								mp_I\Map\CurrChunk = Int(mp_I\MapInList\TriggerAreas[i]\y)-1
							EndIf
						EndIf
					EndIf
				EndIf
			ElseIf Int(mp_I\MapInList\TriggerAreas[i]\y)-1 = mp_I\Map\CurrChunk Then
				If EntityDistanceSquared(mp_I\Map\Triggers[i],mpl\CameraPivot)<PowTwo(7.0) Then
					If EntityInView(mp_I\Map\Triggers[i],Camera) Then
						If Int(mp_I\MapInList\TriggerAreas[i]\x)-1=0 Then
							EntityAlpha GetChild(mp_I\Map\obj,RMESH_BSP),1.0
						Else
							EntityAlpha GetChild(mp_I\Map\Chunks[Int(mp_I\MapInList\TriggerAreas[i]\x)-2],RMESH_BSP),1.0
						EndIf
						
						If CheckSpecificTrigger(mpl\CameraPivot,mp_I\Map\Triggers[i]) Then
							If EntityDistanceSquared(mp_I\Map\TriggerPoint2[i],mpl\CameraPivot)>EntityDistanceSquared(mp_I\Map\TriggerPoint1[i],mpl\CameraPivot) Then
								mp_I\Map\CurrChunk = Int(mp_I\MapInList\TriggerAreas[i]\x)-1
							EndIf
						EndIf
					EndIf
				EndIf
			EndIf
		EndIf
	Next
	
End Function

Function CheckSpecificTrigger%(ent%,trigger%)
	Local isintersect% = False
	
	Local checkSphere% = CreateSphere(2)
	ScaleEntity checkSphere,0.3,0.3,0.3
	PositionEntity checkSphere,EntityX(ent),EntityY(ent),EntityZ(ent)
	
	isintersect% = MeshesIntersect(checkSphere,trigger)
	FreeEntity checkSphere
	
	Return isintersect
End Function

Type PlayerSpawner
	Field yaw#
	Field team%
	Field obj%
	Field hasPlayerSpawned%
End Type

Function CreatePlayerSpawner.PlayerSpawner(x#, y#, z#, yaw#, team%)
	Local ps.PlayerSpawner = New PlayerSpawner
	
	ps\obj = CreatePivot()
	PositionEntity ps\obj,x#,y#,z#,True
	ps\yaw = yaw
	ps\team = team
	
	Return ps
End Function

Function RespawnPlayers()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local ps.PlayerSpawner
	
	Local i%
	
	For i = 0 To (mp_I\MaxPlayers-1)
		If Players(i)<>Null Then
			If Players(i)\CurrHP <= 0 Lor mp_I\Gamemode\Phase <= Waves_StartGame Then
				RespawnPlayer(i)
			EndIf
		EndIf
	Next
	
End Function

Function RespawnPlayer(playerID%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local g_I.GunInstance = First GunInstance
	Local ps.PlayerSpawner
	Local i%
	Local mmt.MultiplayerMapTemplate
	
	TeleportEntity(Players(playerID)\Collider,0,3,0,0.3,True,2.0,0)
	
	Local found% = False
	While (Not found)
		For ps = Each PlayerSpawner
;			If Rand(1,3)=1 And ps\team = Max(Players(playerID)\Team-1,0) And (Not ps\hasPlayerSpawned) Then
;				TeleportEntity(Players(playerID)\Collider,EntityX(ps\obj),EntityY(ps\obj)+0.1,EntityZ(ps\obj),0.3,True,2.0,0)
;				found = True
;				ps\hasPlayerSpawned = True
;				Exit
;			EndIf
			If Rand(1,3)=1 And ps\team = Max(Players(playerID)\Team-1,0) Then
				TeleportEntity(Players(playerID)\Collider,EntityX(ps\obj),EntityY(ps\obj)+0.1,EntityZ(ps\obj),0.3,True,2.0,0)
				found = True
				ps\hasPlayerSpawned = True
				Exit
			EndIf
		Next
	Wend
	
	Players(playerID)\X = EntityX(Players(playerID)\Collider)
	Players(playerID)\Y = EntityY(Players(playerID)\Collider)
	Players(playerID)\Z = EntityZ(Players(playerID)\Collider)
	
	Local beforeWeapon% = (Players(playerID)\WeaponInSlot[SLOT_PRIMARY] <> GUN_P90 Lor Players(playerID)\SelectedSlot <> SLOT_PRIMARY)
	If Players(playerID)\CurrHP <= 0 Then
		Players(playerID)\CurrHP = 100
		Players(playerID)\CurrKevlar = 100
		Players(playerID)\CurrStamina = 100.0
		Players(playerID)\WantsSlot = SLOT_SECONDARY
		Players(playerID)\SelectedSlot = SLOT_SECONDARY
		Players(playerID)\WeaponInSlot[SLOT_PRIMARY] = GUN_UNARMED
		Players(playerID)\WeaponInSlot[Players(playerID)\SelectedSlot] = GUN_USP
		Players(playerID)\WeaponInSlot[SLOT_MELEE] = GUN_KNIFE
		Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = GetWeaponMaxCurrAmmo(Players(playerID)\WeaponInSlot[Players(playerID)\SelectedSlot])
		Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot] = 3
		Players(playerID)\IronSight = False
		If mp_I\Gamemode\ID = Gamemode_Deathmatch Then
			Players(playerID)\WantsSlot = SLOT_PRIMARY
			Players(playerID)\SelectedSlot = SLOT_PRIMARY
			Players(playerID)\WeaponInSlot[Players(playerID)\SelectedSlot] = GUN_P90
			Players(playerID)\Ammo[Players(playerID)\SelectedSlot] = GetWeaponMaxCurrAmmo(Players(playerID)\WeaponInSlot[Players(playerID)\SelectedSlot])
			Players(playerID)\ReloadAmmo[Players(playerID)\SelectedSlot] = 3
		EndIf
	ElseIf mp_I\Gamemode\ID = Gamemode_Deathmatch Then
		Players(playerID)\CurrHP = 100
		Players(playerID)\CurrKevlar = 100
		Players(playerID)\CurrStamina = 100.0
	EndIf
	If playerID = mp_I\PlayerID Then
		DeselectIronSight()
		If beforeWeapon Then
			g_I\GunChangeFLAG = False
		EndIf
	EndIf
	
	If playerID = mp_I\PlayerID Then
		If Players(playerID)\Team = Team_CI Then
			mp_I\Map\CurrChunk = mp_I\MapInList\CISpawn-(mp_I\MapInList\ChunkStart-1)
		Else
			mp_I\Map\CurrChunk = mp_I\MapInList\NTFSpawn-(mp_I\MapInList\ChunkStart-1)
		EndIf
	EndIf
	
End Function

Function ApplyPlayerHitBoxes.HitBox()
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	
	Local hb.HitBox = New HitBox
	hb\NPCtype = -1
	
	Local i%,htype%,bonename$
	Local scaleX#,scaleY#,scaleZ#,posX#,posY#,posZ#
	Local file$ = "Data\PlayerBones.ini"
	
	If NTF_GameModeFlag=3 And mp_I\PlayState=GAME_CLIENT Then Return
	
	For i = 0 To GetINIInt(file$,"Player","hitbox_amount")-1
		htype% = GetINIInt(file$,"Player","hitbox"+(i+1)+"_type")
		bonename$ = GetINIString(file$,"Player","hitbox"+(i+1)+"_parent")
		hb\BoneName[i] = bonename
		If htype = 0
			hb\HitBox1[i] = CreateCube()
			scaleX# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_scaleX",1.0)
			scaleY# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_scaleY",1.0)
			scaleZ# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_scaleZ",1.0)
			posX# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_posX",0.0)
			posY# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_posY",0.0)
			posZ# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_posZ",0.0)
			ScaleEntity hb\HitBox1[i],scaleX,scaleY,scaleZ
			PositionEntity hb\HitBox1[i],posX,posY,posZ
			EntityPickMode hb\HitBox1[i],2
			EntityAlpha hb\HitBox1[i],0.0
			HideEntity hb\HitBox1[i]
		ElseIf htype = 1
			hb\HitBox2[i] = CreateCube()
			scaleX# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_scaleX",1.0)
			scaleY# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_scaleY",1.0)
			scaleZ# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_scaleZ",1.0)
			posX# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_posX",0.0)
			posY# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_posY",0.0)
			posZ# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_posZ",0.0)
			ScaleEntity hb\HitBox2[i],scaleX,scaleY,scaleZ
			PositionEntity hb\HitBox2[i],posX,posY,posZ
			EntityPickMode hb\HitBox2[i],2
			EntityAlpha hb\HitBox2[i],0.0
			HideEntity hb\HitBox2[i]
		Else
			hb\HitBox3[i] = CreateCube()
			scaleX# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_scaleX",1.0)
			scaleY# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_scaleY",1.0)
			scaleZ# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_scaleZ",1.0)
			posX# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_posX",0.0)
			posY# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_posY",0.0)
			posZ# = GetINIFloat(file$,"Player","hitbox"+(i+1)+"_posZ",0.0)
			ScaleEntity hb\HitBox3[i],scaleX,scaleY,scaleZ
			PositionEntity hb\HitBox3[i],posX,posY,posZ
			EntityPickMode hb\HitBox3[i],2
			EntityAlpha hb\HitBox3[i],0.0
			HideEntity hb\HitBox3[i]
		EndIf
		hb\HitBoxPosX[i]=posX
		hb\HitBoxPosY[i]=posY
		hb\HitBoxPosZ[i]=posZ
	Next
	
	Return hb
End Function

Function CopyPlayerHitBoxes(playerid%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local hb.HitBox,bone%,i%
	Local HitBoxName$
	
	If mp_I\PlayState = GAME_CLIENT Then Return
	
	For hb = Each HitBox
		If hb\NPCtype = -1 Then
			For i = 0 To 24
				If hb\BoneName[i]<>"" Then
					;n\BoneName[i]=hb\BoneName[i]
					HitBoxName = hb\BoneName[i]
					bone = FindChild(Players(playerid)\obj_lower,HitBoxName)
					If bone = 0 Then
						bone = FindChild(Players(playerid)\obj_upper,HitBoxName)
					EndIf
				EndIf
				If hb\HitBox1[i]<>0 Then
					Players(playerid)\HitBox1[i] = CopyEntity(hb\HitBox1[i],bone)
					PositionEntity Players(playerid)\HitBox1[i],hb\HitBoxPosX[i],hb\HitBoxPosY[i],hb\HitBoxPosZ[i]
				EndIf
				If hb\HitBox2[i]<>0 Then
					Players(playerid)\HitBox2[i] = CopyEntity(hb\HitBox2[i],bone)
					PositionEntity Players(playerid)\HitBox2[i],hb\HitBoxPosX[i],hb\HitBoxPosY[i],hb\HitBoxPosZ[i]
				EndIf
				If hb\HitBox3[i]<>0 Then
					Players(playerid)\HitBox3[i] = CopyEntity(hb\HitBox3[i],bone)
					PositionEntity Players(playerid)\HitBox3[i],hb\HitBoxPosX[i],hb\HitBoxPosY[i],hb\HitBoxPosZ[i]
				EndIf
			Next
			Exit
		EndIf
	Next
	
End Function

Function HidePlayerHitBoxes(playerid%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local i%
	
	If mp_I\PlayState = GAME_CLIENT Then Return
	
	For i = 0 To 24
		If Players(playerid)\HitBox1[i]<>0 Then HideEntity Players(playerid)\HitBox1[i]
		If Players(playerid)\HitBox2[i]<>0 Then HideEntity Players(playerid)\HitBox2[i]
		If Players(playerid)\HitBox3[i]<>0 Then HideEntity Players(playerid)\HitBox3[i]
	Next
	
End Function

Function ShowPlayerHitBoxes(playerid%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local i%
	
	If mp_I\PlayState = GAME_CLIENT Then Return
	
	For i = 0 To 24
		If Players(playerid)\HitBox1[i]<>0 Then ShowEntity Players(playerid)\HitBox1[i]
		If Players(playerid)\HitBox2[i]<>0 Then ShowEntity Players(playerid)\HitBox2[i]
		If Players(playerid)\HitBox3[i]<>0 Then ShowEntity Players(playerid)\HitBox3[i]
	Next
	
End Function

Function FreePlayerHitBoxes(playerid%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local i%
	
	If mp_I\PlayState = GAME_CLIENT Then Return
	
	For i = 0 To 24
		If Players(playerid)\HitBox1[i]<>0 Then FreeEntity Players(playerid)\HitBox1[i] : Players(playerid)\HitBox1[i]=0
		If Players(playerid)\HitBox2[i]<>0 Then FreeEntity Players(playerid)\HitBox2[i] : Players(playerid)\HitBox2[i]=0
		If Players(playerid)\HitBox3[i]<>0 Then FreeEntity Players(playerid)\HitBox3[i] : Players(playerid)\HitBox3[i]=0
	Next
	
End Function

Function SetupTeam(playerid%)
	CatchErrors("SetupTeam(" + playerid + ")")
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local g.Guns
	Local temp#
	Local i%, sf%, b%, t1%, name$, tex%, teamSTR$
	
	If mp_I\PlayerID = playerid Then
		If Players(mp_I\PlayerID)\Team = Team_CI Then
			teamSTR = "_ci.jpg"
		Else
			teamSTR = ".png"
		EndIf
		tex = LoadTexture_Strict("GFX\weapons\hands" + teamSTR)
		
		For g = Each Guns
			For i = 2 To CountSurfaces(g\obj)
				sf = GetSurface(g\obj,i)
				b = GetSurfaceBrush(sf)
				If b<>0 Then
					t1 = GetBrushTexture(b,BumpEnabled)
					If t1<>0 Then
						name$ = StripPath(TextureName(t1))
						If Left(Lower(name),5) = "hands" Then
							BrushTexture b, tex, 0, BumpEnabled
							PaintSurface sf,b
						EndIf
						If name<>"" Then DeleteSingleTextureEntryFromCache t1
					EndIf
					FreeBrush b
				EndIf
			Next
		Next
		
		DeleteSingleTextureEntryFromCache tex
	EndIf
	
	If Players(playerid)\GunModel<>0 And Players(playerid)\obj_upper<>0 And Players(playerid)\obj_lower<>0 Then
		FreePlayerHitBoxes(playerid)
		FreeEntity Players(playerid)\GunModelMuzzleFlash
		FreeEntity Players(playerid)\GunModel
		Players(playerid)\GunModel = 0
		Players(playerid)\GunModelMuzzleFlash = 0
		FreeEntity Players(playerid)\obj_upper
		FreeEntity Players(playerid)\obj_lower
	EndIf
	
	If Players(playerid)\Team > Team_Spectator Then
		ChangePlayerModel(playerid, Players(playerid)\Team-1)
	EndIf
	
	If mp_I\PlayState = GAME_SERVER Then
		RespawnPlayer(playerid)
	Else
		If mp_I\PlayerID = playerid Then
			If Players(playerid)\Team = Team_CI Then
				mp_I\Map\CurrChunk = mp_I\MapInList\CISpawn-(mp_I\MapInList\ChunkStart-1)
			Else
				mp_I\Map\CurrChunk = mp_I\MapInList\NTFSpawn-(mp_I\MapInList\ChunkStart-1)
			EndIf
		EndIf
	EndIf
	
	SwitchPlayerGun(playerid)
	
	CatchErrors("Uncaught (SetupTeam(" + playerid + "))")
End Function

Function ChangePlayerModel(playerid%, team%)
	Local mp_I.MultiplayerInstance = First MultiplayerInstance
	Local temp#
	
	Players(playerid)\obj_lower = CopyEntity(mp_I\PlayerModel_Lower[team])
	Players(playerid)\obj_upper = CopyEntity(mp_I\PlayerModel_Upper[team])
	temp# = (0.29 / 2.5)
	ScaleEntity Players(playerid)\obj_lower, temp, temp, temp
	MeshCullBox (Players(playerid)\obj_lower, -MeshWidth(Players(playerid)\obj_lower), -MeshHeight(Players(playerid)\obj_lower), -MeshDepth(Players(playerid)\obj_lower), MeshWidth(Players(playerid)\obj_lower)*2, MeshHeight(Players(playerid)\obj_lower)*2, MeshDepth(Players(playerid)\obj_lower)*2)
	ScaleEntity Players(playerid)\obj_upper, temp, temp, temp
	PositionEntity Players(playerid)\obj_upper,0.0,-0.05,-0.06
	EntityParent Players(playerid)\obj_upper,FindChild(Players(playerid)\obj_lower,"hips")
	MeshCullBox (Players(playerid)\obj_upper, -MeshWidth(Players(playerid)\obj_upper), -MeshHeight(Players(playerid)\obj_upper), -MeshDepth(Players(playerid)\obj_upper), MeshWidth(Players(playerid)\obj_upper)*2, MeshHeight(Players(playerid)\obj_upper)*2, MeshDepth(Players(playerid)\obj_upper)*2)
	
	Players(playerid)\AnimState_Lower = -1
	Players(playerid)\AnimState_Upper = -1
	
	CopyPlayerHitBoxes(playerid)
	HidePlayerHitBoxes(playerid)
	
End Function








;~IDEal Editor Parameters:
;~F#4B#87#C1#C7#DA#E8#F4#F8#10D#11F#176#1A9#1E1#20E#22D#243#270#295#2A3#2A8
;~F#2B5#312#320#32D#33A#375#396#3AB#3B8#3CD#4BA#500#558#584#5DE#5E9#70B#79C#7C8#7DA
;~F#92C#9F4#A87#AAA#AF0#B37#B45#BC3#D30#D3B#D97#DAD#DBD#E06#E25#E48#E4D#F18#F45#F8B
;~F#FAE#FC9#FDE#FE7#1022#1036#1054#108F#10D7#10E1#11E4#11F9#1212#1247#127B#1285#1293#12E0#131B#135A
;~F#13FA#141D#142D#1482#14C7#14F9#152A#15CA#1610#1642#165A#169B#1728#172E#1738#1788#17FC#1823#1853#1862
;~F#18F9#193C#19A7#1A32#1AAE#1AF7#1B1E#1B96#1BA4#1BF0#1C07#1C1F#1CD1#1CE3#1D1A#1D67#1D73#1E64#1E71#1E78
;~F#1E83#1E93#1ED6#1F8F#1F9F#1FB3#1FB8#1FCF#1FE4#1FF6#2003#2018#201E#2047#2087#20AC#20BA#20C8#20D6#2118
;~C#Blitz3D