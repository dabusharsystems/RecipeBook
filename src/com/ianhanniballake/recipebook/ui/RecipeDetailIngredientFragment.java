package com.ianhanniballake.recipebook.ui;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.ianhanniballake.recipebook.R;
import com.ianhanniballake.recipebook.model.Ingredient;
import com.ianhanniballake.recipebook.provider.RecipeContract;

/**
 * Fragment which displays the list of ingredients for a given recipe
 */
public class RecipeDetailIngredientFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>
{
	/**
	 * Cursor Adapter which handles the unique binding of ingredients to a single TextView
	 */
	private class IngredientCursorAdapter extends ResourceCursorAdapter
	{
		/**
		 * Standard constructor.
		 * 
		 * @param context
		 *            The context where the ListView associated with this adapter is running
		 * @param layout
		 *            Resource identifier of a layout file that defines the views for this list item. Unless you
		 *            override them later, this will define both the item views and the drop down views.
		 * @param c
		 *            The cursor from which to get the data.
		 * @param flags
		 *            Flags used to determine the behavior of the adapter, as per
		 *            {@link CursorAdapter#CursorAdapter(Context, Cursor, int)}.
		 */
		public IngredientCursorAdapter(final Context context, final int layout, final Cursor c, final int flags)
		{
			super(context, layout, c, flags);
		}

		@Override
		public void bindView(final View view, final Context context, final Cursor cursor)
		{
			final TextView raw = (TextView) view.findViewById(R.id.raw);
			raw.setText(new Ingredient(cursor).toString());
		}
	}

	/**
	 * Adapter to display the list's data
	 */
	private IngredientCursorAdapter adapter;

	@Override
	public void onActivityCreated(final Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		adapter = new IngredientCursorAdapter(getActivity(), R.layout.list_item_ingredient, null, 0);
		setListAdapter(adapter);
		getListView().setChoiceMode(AbsListView.CHOICE_MODE_NONE);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(final int id, final Bundle args)
	{
		final long recipeId = ContentUris.parseId(getActivity().getIntent().getData());
		return new CursorLoader(getActivity(), RecipeContract.Ingredients.CONTENT_URI, null,
				RecipeContract.Ingredients.COLUMN_NAME_RECIPE_ID + "=?", new String[] { Long.toString(recipeId) }, null);
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_ingredient_detail, container, false);
	}

	@Override
	public void onLoaderReset(final Loader<Cursor> loader)
	{
		adapter.swapCursor(null);
	}

	@Override
	public void onLoadFinished(final Loader<Cursor> loader, final Cursor data)
	{
		adapter.swapCursor(data);
	}
}
