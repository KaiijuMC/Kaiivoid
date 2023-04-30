package dev.kugge.kaiivoid.util;

import org.bukkit.entity.Villager;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MerchantSave {
    public final Villager villager;
    private final List<MerchantRecipe> recipes;
    private final Map<MerchantRecipe, Integer> uses = new ConcurrentHashMap<>();
    private final Map<MerchantRecipe, Integer> demand = new ConcurrentHashMap<>();
    private final Map<MerchantRecipe, Float> prizeMultiplier = new ConcurrentHashMap<>();
    private final Map<MerchantRecipe, Integer> specialPrize = new ConcurrentHashMap<>();

    public MerchantSave(final Merchant merchant) {
        villager = (Villager) merchant;
        recipes = merchant.getRecipes();
        for (final MerchantRecipe recipe : recipes) {
            uses.put(recipe, recipe.getUses());
            demand.put(recipe, recipe.getDemand());
            prizeMultiplier.put(recipe, recipe.getPriceMultiplier());
            specialPrize.put(recipe, recipe.getSpecialPrice());
        }
    }

    public void resetMerchant() {
        for (final MerchantRecipe recipe : recipes) {
            recipe.setUses(uses.get(recipe));
            recipe.setDemand(demand.get(recipe));
            recipe.setPriceMultiplier(prizeMultiplier.get(recipe));
            recipe.setSpecialPrice(specialPrize.get(recipe));
        }
    }
}
