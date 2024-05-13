package com.gildedrose;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
	private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	public static final String CONJURED = "Conjured Mana Cake";
	private Item[] items;

	public GildedRose(Item... items) {
		this.items = items;
	}
	public Item[] getItems() {
		return items;
	} 

    public void updateQuality() {
        for (Item item: items) {
			updateQualityOfItem(item);
        }
    }

	private void updateQualityOfItem(Item item) {
		int degradeRate = item.name.equals(CONJURED) ? -2 : -1;
		boolean whenItemDegrades = !item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES) && !item.name.equals(SULFURAS_HAND_OF_RAGNAROS);
		if (whenItemDegrades) {
			calculateQuality(item, degradeRate);
		}
		if (item.name.equals(AGED_BRIE)) {
			calculateQuality(item, 1);
		}

		if (item.name.equals(BACKSTAGE_PASSES)) {
			calculateQuality(item, 1);
			if (item.sellIn < 11) {
				calculateQuality(item, 1);
			}

			if (item.sellIn < 6) {
				calculateQuality(item, 1);
			}
		}

		if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			item.sellIn = item.sellIn - 1;
		}

		if (item.sellIn < 0) {
			if (whenItemDegrades) {
				calculateQuality(item, degradeRate);
			}
			if (item.name.equals(AGED_BRIE)) {
				calculateQuality(item, 1);
			} else if (item.name.equals(BACKSTAGE_PASSES)) {
				item.quality = item.quality - item.quality;
			}
		}
	}

	private void calculateQuality(Item item, int changeInQuality) {
		int updatedQuality = item.quality + changeInQuality;
		boolean inValidRange = updatedQuality <= 50 && updatedQuality >= 0;
		if (inValidRange) {
			item.quality = updatedQuality;
		}
	}
}
