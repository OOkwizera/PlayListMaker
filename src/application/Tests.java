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
	PlayList play4 = new PlayList(play3);
	Catalog cat2 = new Catalog(cat);

	

	@Test
	public void testPlayListsBasic() {
		assertTrue(play4.equals(play3));
		assertEquals("Favorites", play.getTitle());
		assertEquals("No Title", play2.getTitle());
		play.setTitle("ChangeName");
		assertEquals("ChangeName", play.getTitle());
		play.addSong(song1);
		play2.addSong(song1);
		play2.clear();
		assertEquals(0, play2.getNumSongs());
		play3.addSong(song1);
		play3.addSong(song2);
		play3.addSong(song3);
		play4.addSong(song1);
		play4.addSong(song2);
		PlayList play5 = new PlayList(play3);
		assertTrue(play5.equals(play3));
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
		assertEquals(2, cat.size());
		cat.addPlayList(play3);
		cat.removePlayList(play);
		assertEquals(2, cat.size());
		assertEquals(cat.getCatalog().get(1).getTitle(), "Favorites");
	}
	
	@Test
	public void testCatalogDynamic() {
		play.addSong(song1);
		String strr = play.toString();
		cat.addPlayListFrom(strr);
		cat2.addPlayListFrom(strr);
		assertTrue(cat.equals(cat2));
		cat2.addPlayList(play2);
		int hash = cat.hashCode();
		assertNotEquals(hash, cat2.hashCode());
		
	}

}

