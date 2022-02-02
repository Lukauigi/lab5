package com.bcit.lukasz_bednarek_lab4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private String[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * This template comes with a TextView
     */
    /* The ViewHolder is the method which gathers references of the layout's views */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // instance variables to assign here
        private final TextView textView;
        private final Switch toggle;
        private final Button button;

        public ViewHolder(View view) {
            super(view);

            /* assigns the views to these variables */
            textView = view.findViewById(R.id.textView_item_row_number);
            toggle = view.findViewById(R.id.switch_item_status);
            button = view.findViewById(R.id.button_item);
        }

        /* these are getters which are used in onBindViewHolder method. A method which
        * allows you to set and get views and build logic around them. That is where you can
        * use views and events. */
        public TextView getTextView() { return textView; }

        public Switch getSwitchView() { return toggle; }

        public Button getButtonView() { return button; }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public ItemAdapter(String[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_switchy, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        // this basically sets every textView text to each index element

        /* for example this looks into the viewHolder class that is instantiated when the
        * view is made (I think), and it uses the textView's getter to obtain that view
        * from the layout (item_sketchy.xml). Since we can access it here, we can set the
        * its text to the data we pass through the adapter in MainActivity. Each index
        * element will be each textView's text (comes along with a switch & button). */
        viewHolder.getTextView().setText(localDataSet[position]);

        // these get the button & switch views
        Switch rowToggle = viewHolder.getSwitchView();
        Button button = viewHolder.getButtonView();

        /* these are event listeners for the button and switch. the param in the OnClickListener
        * are the classes that will trigger logic below.
        *
        * the switch/button hit will be passed to the classes' OnClick view param.*/

        // we pass the viewHolder (the class above which holds the views of the layout) when switch is clicked.
        // this executes the method in the class.
        rowToggle.setOnClickListener(new toggleListener(viewHolder));
        // Again, the button(s) from the layout wait for a click. Once clicked,
        // the class is instantiated and method is executed.
        button.setOnClickListener(new buttonListener());


    }

    private static class toggleListener implements View.OnClickListener {
        private final ViewHolder itemViews;

        toggleListener(final ViewHolder viewHolder) {
            itemViews = viewHolder;
        }

        @Override
        public void onClick(final View view) {
            final Switch hitSwitch = (Switch) view;
            final Button associatedButton = itemViews.getButtonView();

            if (hitSwitch.isChecked()) {
                hitSwitch.setText("Enabled");
                associatedButton.setEnabled(true);
            } else {
                hitSwitch.setText("Disabled");
                associatedButton.setText("D:");
                associatedButton.setEnabled(false);
            }
        }
    }

    private static class buttonListener implements View.OnClickListener {
        @Override
        public void onClick(final View view) {
            Button hitButton = (Button) view;
            if (hitButton.isEnabled()) {
                hitButton.setText(":D");
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}