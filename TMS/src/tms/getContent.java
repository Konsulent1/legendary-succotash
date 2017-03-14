package tms;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class getContent {
	
	private int contentWidth;
	private int contentHeight;
	
	public getContent(int width, int height){
		this.contentWidth = width;
		this.contentHeight = height;
	}

	public Pane getHome(){
		ContentBox home = new ContentBox(contentWidth, contentHeight);
		home.setFill(Color.DARKRED);
		//home.setFill(Color.TRANSPARENT);
		return home;	
	}
	
	public Pane getSchedule(){
		ContentBox schedule = new ContentBox(contentWidth, contentHeight);
		schedule.setFill(Color.DARKSLATEGRAY);
		return schedule;		
	}
	
	public Pane getLoad(){
		ContentBox load = new ContentBox(contentWidth, contentHeight);
		load.setFill(Color.DIMGREY);
		return load;		
	}
	
	public Pane getUnload(){
		ContentBox unload = new ContentBox(contentWidth, contentHeight);
		unload.setFill(Color.DARKKHAKI);
		return unload;		
	}
	
	public Pane getExportDocument(){
		ContentBox exportDocument = new ContentBox(contentWidth, contentHeight);	
		exportDocument.setFill(Color.DARKCYAN);		
		return exportDocument;		
	}
}