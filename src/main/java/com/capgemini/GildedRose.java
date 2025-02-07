package com.capgemini;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        boolean isAgedBrie, isBackstage, isSulfuras;
        for (Item item : items) {
            isAgedBrie = item.name.equals("Aged Brie");
            isBackstage = item.name.equals("Backstage passes to a TAFKAL80ETC concert");
            isSulfuras = item.name.equals("Sulfuras, Hand of Ragnaros");
            if(isSulfuras) {
                //don't do anything
                continue;
            }
            item.sellIn = item.sellIn - 1;
            if(isAgedBrie) {
                handleAgedBrie(item);
            }
            else if(isBackstage) {
                handleBackstage(item);
            }
            else {
                handleRest(item);
            }
        }
    }

    private void handleRest(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
        if (item.sellIn < 0) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
        }
    }

    private void handleBackstage(Item backstage) {
        if (backstage.quality < 50) {
            backstage.quality = backstage.quality + 1;
            if (backstage.sellIn < 10) {
                if (backstage.quality < 50) {
                    backstage.quality = backstage.quality + 1;
                }
            }

            if (backstage.sellIn < 5) {
                if (backstage.quality < 50) {
                    backstage.quality = backstage.quality + 1;
                }
            }
        }
        if(backstage.sellIn < 0) {
            backstage.quality = 0;   //isBackstage
        }
    }

    private void handleAgedBrie(Item agedBrie) {
        if (agedBrie.quality < 50) {
            agedBrie.quality = agedBrie.quality + 1;
        }
        if (agedBrie.sellIn < 0) {
            if (agedBrie.quality < 50) {
                agedBrie.quality = agedBrie.quality + 1;
            }
        }
    }
}
