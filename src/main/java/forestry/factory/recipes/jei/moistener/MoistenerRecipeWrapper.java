package forestry.factory.recipes.jei.moistener;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import forestry.api.fuels.MoistenerFuel;
import forestry.api.recipes.IMoistenerRecipe;
import forestry.core.recipes.jei.ForestryRecipeWrapper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class MoistenerRecipeWrapper extends ForestryRecipeWrapper<IMoistenerRecipe> {
	
	@Nonnull
	private final MoistenerFuel fuel;
	
	public MoistenerRecipeWrapper(@Nonnull IMoistenerRecipe recipe, @Nonnull MoistenerFuel fuel) {
		super(recipe);
		this.fuel = fuel;
	}

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		IMoistenerRecipe recipe = getRecipe();

		List<ItemStack> itemStackInputs = new ArrayList<>();
		itemStackInputs.add(recipe.getResource());
		ItemStack fuelItem = fuel.getItem();
		if (fuelItem != null) {
			itemStackInputs.add(fuelItem);
		}
		ingredients.setInputs(ItemStack.class, itemStackInputs);

		List<ItemStack> itemStackOutputs = new ArrayList<>();
		itemStackOutputs.add(recipe.getProduct());
		ItemStack fuelProduct = fuel.getProduct();
		if (fuelProduct != null) {
			itemStackOutputs.add(fuelProduct);
		}
		ingredients.setOutputs(ItemStack.class, itemStackOutputs);

		ingredients.setInput(FluidStack.class, new FluidStack(FluidRegistry.WATER, recipe.getTimePerItem() / 4));
	}

	@Nonnull
	public MoistenerFuel getFuel() {
		return fuel;
	}

}
