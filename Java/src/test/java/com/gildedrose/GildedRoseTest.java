package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

	@Test
	public void sellInDateDecreases_butQualityCannotBeNegative() {
		GildedRose app = new GildedRose(new Item("foo", 0, 0));
		app.updateQuality();
		assertItemEquals(app.getItems()[0], new Item("foo", -1, 0));
	}

	@Test
	public void qualityDecreases() {
		GildedRose app = new GildedRose(new Item("foo", 10, 10));
		app.updateQuality();
		assertItemEquals(app.getItems()[0], new Item("foo", 9, 9));
	}

	@Test
	public void qualityDecreasesFasterAfterSellInDateExpired() {
		GildedRose app = new GildedRose(new Item("foo", 0, 10));
		app.updateQuality();
		assertItemEquals(app.getItems()[0], new Item("foo", -1, 8));
	}

	@Test
	public void item_AgedBrie_increasesInQuality() {
		GildedRose app = new GildedRose(new Item("Aged Brie", 2, 2));
		app.updateQuality();
		assertItemEquals(app.getItems()[0], new Item("Aged Brie", 1, 3));
	}

	@Test
	public void item_AgedBrie_increasesInQuality_DoublesWhenOff() {
		GildedRose app = new GildedRose(new Item("Aged Brie", 0, 2));
		app.updateQuality();
		assertItemEquals(app.getItems()[0], new Item("Aged Brie", -1, 4));
	}

	@Test
	public void item_AgedBrie_cannotGoOver50Quality() {
		GildedRose app = new GildedRose(new Item("Aged Brie", 2, 50));
		app.updateQuality();
		assertItemEquals(app.getItems()[0], new Item("Aged Brie", 1, 50));
	}
	
	@Test
	public void item_BackStagePasses_increasesInQuality_byOneOutside10Days() {
		GildedRose app = new GildedRose(new Item("Backstage passes to a TAFKAL80ETC concert", 20, 2));
		app.updateQuality();
		assertItemEquals(app.getItems()[0], new Item("Backstage passes to a TAFKAL80ETC concert", 19, 3));
	}

	@Test
	public void item_BackStagePasses_increasesInQuality_byTwoInside10Days() {
		GildedRose app = new GildedRose(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 2));
		app.updateQuality();
		assertItemEquals(app.getItems()[0], new Item("Backstage passes to a TAFKAL80ETC concert", 9, 4));
	}

	@Test
	public void item_BackStagePasses_increasesInQuality_byThreeInside5Days() {
		GildedRose app = new GildedRose(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 2));
		app.updateQuality();
		assertItemEquals(app.getItems()[0], new Item("Backstage passes to a TAFKAL80ETC concert", 4, 5));
	}

	@Test
	public void item_BackStagePasses_increasesInQuality_goesToZeroWhenSellInExpires() {
		GildedRose app = new GildedRose(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
		app.updateQuality();
		assertItemEquals(app.getItems()[0], new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0));
	}
	
	public static void assertItemEquals(Item actual, Item expected) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.quality, actual.quality);
		assertEquals(expected.sellIn, actual.sellIn);
	}

}
