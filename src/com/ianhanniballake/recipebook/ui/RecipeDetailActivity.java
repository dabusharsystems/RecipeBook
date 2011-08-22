package com.ianhanniballake.recipebook.ui;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ianhanniballake.recipebook.R;
import com.ianhanniballake.recipebook.provider.RecipeContract;

/**
 * Activity which displays only the Recipe details
 */
public class RecipeDetailActivity extends FragmentActivity implements
		OnRecipeEditListener
{
	/**
	 * Sets the main layout
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_detail);
	}

	/**
	 * Handles edit cancel events
	 * 
	 * @see com.ianhanniballake.recipebook.ui.OnRecipeEditListener#onRecipeEditCancelled()
	 */
	@Override
	public void onRecipeEditCancelled()
	{
		// TODO Add switch from edit mode
	}

	/**
	 * Handles recipe save events
	 * 
	 * @see com.ianhanniballake.recipebook.ui.OnRecipeEditListener#onRecipeEditSave(long,
	 *      android.content.ContentValues)
	 */
	@Override
	public void onRecipeEditSave(final long recipeId, final ContentValues values)
	{
		final Uri updateUri = ContentUris.withAppendedId(
				RecipeContract.Recipes.CONTENT_ID_URI_PATTERN, recipeId);
		getContentResolver().update(updateUri, values, null, null);
		// TODO Add switch from edit mode
	}

	/**
	 * Handles start recipe edit events.
	 * 
	 * @see com.ianhanniballake.recipebook.ui.OnRecipeEditListener#onRecipeEditStarted(long)
	 */
	@Override
	public void onRecipeEditStarted(final long recipeId)
	{
		// TODO Add switch to edit mode
	}
}