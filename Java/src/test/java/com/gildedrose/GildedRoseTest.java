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

	public static void assertItemEquals(Item actual, Item expected) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.quality, actual.quality);
		assertEquals(expected.sellIn, actual.sellIn);
	}

}
