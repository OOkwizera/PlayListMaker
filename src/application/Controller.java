package application;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import application.net.*;

public class Controller {

	private Catalog catalog = new Catalog();
	private PlayList selectedPlayList = catalog.getCatalog().get(0);
	private Song selectedSong;
	
	@FXML
	private SplitPane canvas;
	@FXML
	private GridPane playListGrid;
	@FXML
	private GridPane songGrid;
	
	private boolean addFlag = false;
	private boolean editFlag = false;
	
	private TextField enterTitle = new TextField();
	private TextField enterSongName = new TextField();
	private TextField enterArtist = new TextField();
	private TextField titleLabel = new TextField();
	private TextField songNameLabel = new TextField();
	private TextField artistLabel = new TextField();
	
	@FXML 
	private TableView<PlayList> catalogTable;
	@FXML
	private TableColumn<PlayList, String> playListTitle; 
	@FXML 
	private TableView<Song> playListTable;
	@FXML
	private TableColumn<Song, String> songTitle;
	@FXML
	private TableColumn<Song, String> songArtist;
	
	@FXML
	public void initialize() {
		
		catalogTable.setItems(catalog.getCatalog());
		
		playListTitle.setCellValueFactory(
				cellData -> cellData.getValue().titleProperty());
		songTitle.setCellValueFactory( 
				cellData -> cellData.getValue().getSongNameProperty());
		
		songArtist.setCellValueFactory(
				cellData -> cellData.getValue().getArtistProperty());

		
		canvas.setOnKeyPressed(k -> handlePress(k.getCode()));
		
		playListTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setSelectedSong(newValue));
		
		catalogTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setSelectedPlayList(newValue));
		
		initTextFields();
	}
	
	public void initTextFields() {
		enterTitle.setPrefHeight(31);
		enterTitle.setPrefWidth(100);
		enterSongName.setPrefHeight(31);
		enterSongName.setPrefWidth(150);
		enterArtist.setPrefHeight(31);
		enterArtist.setPrefWidth(150);
		titleLabel.setPrefHeight(31);
		titleLabel.setPrefWidth(100);
		songNameLabel.setPrefHeight(31);
		songNameLabel.setPrefWidth(150);
		artistLabel.setPrefHeight(31);
		artistLabel.setPrefWidth(150);
	
		titleLabel.setText("Name: ");
		songNameLabel.setText("Title: ");
		artistLabel.setText("Artist: ");
		
		titleLabel.setEditable(false);
		songNameLabel.setEditable(false);
		artistLabel.setEditable(false);
		
	}
	
	public void setSelectedPlayList(PlayList playList) {
		selectedPlayList = playList;
		playListTable.setItems(selectedPlayList.getPlaylist());
	}
	
	public void setSelectedSong(Song song) {
		selectedSong = song;
	}
	
	@FXML
	public void handlePress(KeyCode code) {
		if (code == KeyCode.ENTER && addFlag) {
			addPlayList();
			addSong();
			addFlag = false;
			clearLabels(); 
		}
		
		if (code == KeyCode.ENTER && editFlag) {
			editPlayList();
			editSong();
			editFlag = false;
			clearLabels();
		}
		
		if (code == KeyCode.ESCAPE && (editFlag || addFlag)) {
			editFlag = false;
			addFlag = false;
			clearLabels();
			enterTitle.clear();
			enterSongName.clear();
			enterArtist.clear();
		}
		
	}
	
	public void deletePlayList(){
		if (catalog.getCatalog().contains(selectedPlayList)) {
			selectedPlayList.clear();
			catalog.removePlayList(selectedPlayList);
			
		}
	}
	
	public void deleteSong(){
		if (selectedPlayList.getPlaylist().contains(selectedSong)) {
			selectedPlayList.removeSong(selectedSong);
		
		}
	} 
	
	public void addPlayList(){
		if (!enterTitle.getText().isEmpty()) {
			PlayList playList = new PlayList(enterTitle.getText()); 
			catalog.addPlayList(playList);
			enterTitle.clear();
		}
	}
	
	public void editPlayList(){
		if (!enterTitle.getText().isEmpty()){
			selectedPlayList.setTitle(enterTitle.getText());
			enterTitle.clear();
		}
	}
	
	public void addSong(){ 
		if (!enterSongName.getText().isEmpty()) {
			Song song = new Song(enterSongName.getText(), enterArtist.getText()); 
			selectedPlayList.addSong(song);
			enterSongName.clear();
			enterArtist.clear();
		}
	}
	
	public void editSong(){
		if (!enterSongName.getText().isEmpty() && 
				!enterArtist.getText().isEmpty()
				&& selectedSong != null) {
			selectedSong.setArtist(enterArtist.getText());
			selectedSong.setSongName(enterSongName.getText());
			enterSongName.clear();
			enterArtist.clear();
		}
	}
	
	public void addSongLabelsButton() {
		addSongLabels();
		addFlag = true;
		editFlag = false;
	}
	
	public void editSongLabelsButton() {
		addSongLabels();
		enterSongName.setText(selectedSong.getSongName());
		enterArtist.setText(selectedSong.getArtist());
		editFlag = true;
		addFlag = false;
	}
	
	public void addPlayListLabelsButton() {
		addPlayListLabels(); 
		addFlag = true;
		editFlag = false;
	}
	
	public void editPlayListLabelsButton() {
		addPlayListLabels();
		enterTitle.setText(selectedPlayList.getTitle());
		editFlag = true;
		addFlag = false;
	}
	
	public void addPlayListLabels() {
		if (playListGrid.getChildren().isEmpty()) {
			playListGrid.add(enterTitle, 1, 0);
			playListGrid.add(titleLabel, 0, 0);
		}
	}
	
	public void addSongLabels() {
		if (songGrid.getChildren().isEmpty()) {
			songGrid.add(enterSongName, 1, 0);
			songGrid.add(songNameLabel, 0, 0);
			songGrid.add(enterArtist, 1, 1);
			songGrid.add(artistLabel, 0, 1);
		}
	}
	
	public void clearLabels() {
		songGrid.getChildren().clear();
		playListGrid.getChildren().clear();
	}
	
	public void sharePlayList(){
	}
	
}
