
Function FillRoom_Room2_Offices_4(r.Rooms)
	Local d.Doors
	
	d.Doors = CreateDoor(0, r\x - 240.0 * RoomScale, 0.0, r\z, 90, r, False)
	d\open = False : d\AutoClose = False 
	
End Function

;~IDEal Editor Parameters:
;~F#1
;~C#Blitz3D