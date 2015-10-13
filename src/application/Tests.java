package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	PlayList play = new PlayList("Favorites");
	PlayList play2 = new PlayList("");
	PlayList play3 = new PlayList("Favorites");
	
	Song song1 = new Song("Yesterday", "Beatles");
	Song song2 = new Song("Pyramids", "Frank Ocean");
	Song song3 = new Song("Help!", "");
	Song song4 = new Song("Help!:Beatles");
	Song song5 = new Song("JustATitle");
	
	Catalog cat = new Catalog();

	

	@Test
	public void testPlayListsBasic() {
		assertEquals("Favorites", play.getTitle());
		assertEquals("No Title", play2.getTitle());
		play.setTitle("ChangeName");
		assertEquals("ChangeName", play.getTitle());
		play.addSong(song1);
		play2.addSong(song1);
		assertTrue(play.equals(play2));
		play2.clear();
		play2.addSong(song1);
		assertTrue(play.equals(play2));
	}
	
	@Test
	public void testPlayListDynamic() {
		assertEquals(play.getNumSongs(), 0);
		assertEquals(play2.getNumSongs(), 0);
		play.addSong(song1);
		int hash = play.hashCode();
		assertEquals(hash, play.hashCode());
		play.removeSong(song1);
		play.addSong(song2);
		assertNotEquals(hash, play.hashCode());
		play.setTitle("ChangeName");
		assertEquals("StringProperty [value: ChangeName]", play.titleProperty().toString());
	}
	
	@Test
	public void testSongBasic() {
		assertEquals("Unknown", song3.getArtist());
		song3.setArtist("Beatles");
		song3.setSongName("Yesterday");
		assertTrue(song1.equals(song3));
	}
	
	@Test
	public void testSongDynamic() {
		assertEquals("StringProperty [value: Yesterday]", song1.getSongNameProperty().toString());
		assertEquals("StringProperty [value: Beatles]", song1.getArtistProperty().toString());
		int hash = song1.hashCode();
		assertNotEquals(hash, song3.hashCode());
		assertEquals(song4.getArtist(), "Beatles");
		assertEquals(song5.getArtist(), "Unknown");
	}
	
	@Test 
	public void testCatalog() {
		play.addSong(song1);
		play.addSong(song2);
		play3.addSong(song1);
		cat.addPlayList(play);
		assertEquals(1, cat.size());
		cat.addPlayList(play3);
		cat.removePlayList(play);
		assertEquals(1, cat.size());
		assertEquals(cat.getCatalog().get(0).getTitle(), "Favorites");
	}

}

