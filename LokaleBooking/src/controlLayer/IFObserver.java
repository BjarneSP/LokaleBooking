package controlLayer;

import modelLayer.EnumRoomStatus;
import modelLayer.EnumWeekDay;

public interface IFObserver {
	public void update(EnumRoomStatus rStatus, EnumWeekDay wDay);	
}
