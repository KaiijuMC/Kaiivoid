package dev.kugge.kaiivoid.util;

import org.bukkit.entity.Villager;import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MerchantSnapshot {
    public final Villager villager;
    private final List<MerchantRecipe> recipes;
    private final ConcurrentHashMap<MerchantRecipe, Integer> uses;
    private final ConcurrentHashMap<MerchantRecipe, Integer> demand;
    private final ConcurrentHashMap<MerchantRecipe, Float> prizeMultiplier;
    private final ConcurrentHashMap<MerchantRecipe, Integer> specialPrize;

    public MerchantSnapshot(final Merchant merchant) {
        this.villager = (Villager) merchant;
        this.recipes = merchant.getRecipes();
        this.uses = new ConcurrentHashMap<>();
        this.demand = new ConcurrentHashMap<>();
        this.prizeMultiplier = new ConcurrentHashMap<>();
        this.specialPrize = new ConcurrentHashMap<>();
        for (final MerchantRecipe recipe : this.recipes) {
            this.uses.put(recipe, recipe.getUses());
            this.demand.put(recipe, recipe.getDemand());
            this.prizeMultiplier.put(recipe, recipe.getPriceMultiplier());
            this.specialPrize.put(recipe, recipe.getSpecialPrice());
        }
    }

    public void resetMerchant() {
        for (final MerchantRecipe recipe : this.recipes) {
            recipe.setUses(this.uses.get(recipe));
            recipe.setDemand(this.demand.get(recipe));
            recipe.setPriceMultiplier(this.prizeMultiplier.get(recipe));
            recipe.setSpecialPrice(this.specialPrize.get(recipe));
        }
    }
}