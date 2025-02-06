package com.capgemini;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    Item[] items;
    GildedRose app;

    @Before
    public void setUp() {
        items = new Item[] {
                new Item("Sulfuras, Hand of Ragnaros", 0, 45),
                new Item("Aged Brie", 10, 0),
                new Item("Aged Brie", 10, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 9, 15),
                new Item("Iron Man", 7, 25),
                new Item("Black Widow", 0, 20),
                new Item("Hawkeye", 0, 0)
        };
        app = new GildedRose(items);
    }

    //At the end of each day our system lowers both values for every item
    @Test
    public void testQualityAndSellInDecreasesForNormals() {
        app.updateQuality();
        assertEquals(24, items[4].quality);
        assertEquals(6, items[4].sellIn);
    }

    //Once the sell by date has passed, Quality degrades twice as fast
    @Test
    public void testQualityDecreasesTwiceAsFastAfterSellInForNormals() {
        app.updateQuality();
        assertEquals(18, items[5].quality);
    }

    //The Quality of an item is never negative
    @Test
    public void testQualityIsNeverNegativeForNormals() {
        app.updateQuality();
        assertEquals(0, items[6].quality);
    }

    // “Aged Brie” actually increases in Quality the older it gets
    @Test
    public void testAgedBrieIncreasesInQuality() {
        app.updateQuality();
        assertEquals(1, items[1].quality);
    }

    // The Quality of an item is never more than 50
    @Test
    public void testQualityNeverGoesOver50() {
        app.updateQuality();
        assertEquals(50, items[2].quality);
    }

    //“Sulfuras”, being a legendary item, never has to be sold or decreases in Quality
    @Test
    public void testSulfurasNeverDecreasesInQuality() {
        app.updateQuality();
        assertEquals(45, items[0].quality);
    }

}
