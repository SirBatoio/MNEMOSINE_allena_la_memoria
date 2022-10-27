package com.example.mnemosine_allena_la_memoria;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Associazioni extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener {

   private TextView textView;
   private Button button;

    // Create a string for the TextView and Button label
    private static final String TEXTVIEW_TAG = "DRAG TEXT";
    private static final String BUTTON_TAG = "DRAG BUTTON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associazioni);

        //find views by id
        textView = findViewById(R.id.tv);
        button = findViewById(R.id.btn_draggable);

        // Sets the tag
        textView.setTag(TEXTVIEW_TAG);
        button.setTag(BUTTON_TAG);

        implementEvents();
    }

    //Implement long click and drag listener
    private void implementEvents() {

        textView.setOnLongClickListener(this);
        button.setOnLongClickListener(this);

        findViewById(R.id.top_layout).setOnDragListener(this);
        findViewById(R.id.bottom_layout).setOnDragListener(this);
    }


    // This is the method that the system calls when it dispatches a drag event to the
    // listener.
    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        // Defines a variable to store the action type for the incoming event
        int action = dragEvent.getAction();

        // Handles each of the expected events
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:

                // Determines if this View can accept the dragged data
                if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept
                    // data.
                    //view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                    // Invalidate the view to force a redraw in the new tint
                    // view.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    return true;
                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:

                // Applies a MAGENTA or any color tint to the View,
                // Return true; the return value is ignored.

                view.getBackground().setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_IN);

                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                // Ignore the event
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                // Re-sets the color tint to blue, if you had set the BLUE color or any color in ACTION_DRAG_STARTED. Returns true; the return value is ignored.

                //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                //If u had not provided any color in ACTION_DRAG_STARTED then clear color filter.
                view.getBackground().clearColorFilter();

                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                return true;

            case DragEvent.ACTION_DROP:

                // Gets the item containing the dragged data
                ClipData.Item item = dragEvent.getClipData().getItemAt(0);

                // Gets the text data from the item.
                String dragData = item.getText().toString();

                // Displays a message containing the dragged data.
                Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                // Turns off any color tints
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                //get dragged view
                View v = (View) dragEvent.getLocalState();
                ViewGroup owner = (ViewGroup) v.getParent();
                owner.removeView(v); //remove the dragged view
                LinearLayout container = (LinearLayout) view; //caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                container.addView(v); //Add the dragged view
                v.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE

                // Returns true. DragEvent.getResult() will return true.
                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                // Turns off any color tinting
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                // invoke getResult(), and displays what happened.
                if (dragEvent.getResult())
                    Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();


                // returns true; the value is ignored.
                return true;

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }


    @Override
    public boolean onLongClick(View view) {

        // Create a new ClipData.Item from the tag
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);

        // Instantiates the drag shadow builder.
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        // Starts the drag
        view.startDrag(data //data to be dragged
                , shadowBuilder //drag shadow
                , view //local data about the drag and drop operation
                , 0 //flags (not currently used, set to 0)
        );

        //Set view visibility to INVISIBLE as we are going to drag the view
        view.setVisibility(View.INVISIBLE);

        return true;
    }
}