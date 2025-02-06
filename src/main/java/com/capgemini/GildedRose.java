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
            if(isBackstage) {
                handleBackStage(item);
            }
            else if(isAgedBire) {
                handleAgedBrie(item);
            }
            else {
                if (item.quality > 0) {
                    if (!isSulfuras) {
                        decreaseQuality(item);
                    }
                }
            }

            if (!isSulfuras) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!isAgedBire) {
                    if (!isBackstage) {
                        if (item.quality > 0) {
                            if (!isSulfuras) {
                                decreaseQuality(item);
                            }
                        }
                    }
                }
            }
        }
    }

    private void handleAgedBrie(Item agedBrie) {
        if (agedBrie.quality < 50) {
            increaseQuality(agedBrie);
        }
        if (agedBrie.sellIn < 0 && agedBrie.quality < 50) {
            increaseQuality(agedBrie);
        }
    }

    private void handleBackStage(Item backstage) {
        if (backstage.quality < 50) {
            increaseQuality(backstage);

            if (backstage.sellIn < 11) {
                if (backstage.quality < 50) {
                    increaseQuality(backstage);
                }
            }

            if (backstage.sellIn < 6) {
                if (backstage.quality < 50) {
                    increaseQuality(backstage);
                }
            }
        }
        if (backstage.sellIn < 0) {
            backstage.quality = 0;
        }
    }

    private void decreaseQuality(Item item) {
        item.quality = item.quality - 1;
    }

    private void increaseQuality(Item item) {
        item.quality = item.quality + 1;
    }
}
