package com.capgemini;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        boolean isAgedBire, isBackstage, isSulfuras;
        for (Item item : items) {
            isAgedBire = item.name.equals("Aged Brie");
            isBackstage = item.name.equals("Backstage passes to a TAFKAL80ETC concert");
            isSulfuras = item.name.equals("Sulfuras, Hand of Ragnaros");
            if(isSulfuras) {
                //don't do anything
                continue;
            }
            if(isAgedBire) {
                handleAgedBrie(item);
            }
            else if(isBackstage) {
                handleBackstage(item);
            }
            else {
                if (item.quality > 0) {
                    item.quality = item.quality - 1;
                }
            }


            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                if (!isAgedBire) {
                    if (!isBackstage) {
                        if (item.quality > 0) {
                            item.quality = item.quality - 1;
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    private void handleBackstage(Item backstage) {
        if (backstage.quality < 50) {
            backstage.quality = backstage.quality + 1;
            if (backstage.sellIn < 11) {
                if (backstage.quality < 50) {
                    backstage.quality = backstage.quality + 1;
                }
            }

            if (backstage.sellIn < 6) {
                if (backstage.quality < 50) {
                    backstage.quality = backstage.quality + 1;
                }
            }
        }
    }

    private void handleAgedBrie(Item agedBrie) {
        if (agedBrie.quality < 50) {
            agedBrie.quality = agedBrie.quality + 1;
        }
    }
}
