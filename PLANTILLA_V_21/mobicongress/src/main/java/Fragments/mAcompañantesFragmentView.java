package Fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import DB.mEvent;
import mc.mCongressDemo.MainActivity;
import mc.mCongressDemo.R;

/**
 * Created by luisgonzalez on 11-03-14.
 */

public class mAcompañantesFragmentView extends Fragment implements AdapterView.OnItemClickListener {

    private static final String INDEX = "index";
    private ListView Directorio;
    private Fragment fragment;
    private Cursor C;
    private View RootView;
    mDirectorioAdapter adapter;
    MainActivity activity;

    public static mAcompañantesFragmentView newInstance(MainActivity activity, int index) {



        mAcompañantesFragmentView fragment = new mAcompañantesFragmentView();
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);


       // activity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       this.RootView = inflater.inflate(R.layout.hotel_list_layout, container,false);

        Directorio = (ListView) RootView.findViewById(R.id.commonListView);

        activity = (MainActivity)getActivity();
        activity.setTituloVista(getString(R.string.Accompanist));

        C = activity.getDbHandler().getCursorAcompanantes();


        adapter = new mDirectorioAdapter(activity.getApplicationContext(),C,R.layout.cell_acomp);
        Directorio.setAdapter(adapter);

        // Assign adapter to ListView

        Directorio.setOnItemClickListener(this);

        return  RootView;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        adapter.getCursor().moveToPosition(i);
        mEvent c = new mEvent(adapter.getCursor());

        /*Toast toast1 =
                Toast.makeText(getActivity().getApplicationContext(),
                        "id "+ c.id+ " posicion "+IPVS , Toast.LENGTH_SHORT);

        toast1.show();*/






        MainActivity activity = (MainActivity)getActivity();



        fragment = mDetalleAcompFragmentView.newInstance(activity,c.id);


        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack(null);
        ft.commit();


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity)activity).setHideNavigationBar();
        ((MainActivity)activity).setTituloVista(getString(R.string.Accompanist));
    }

    /*
*    adaptador de datos
*/

    public class mDirectorioAdapter extends CursorAdapter{

        private LayoutInflater inflater;
        private int layout;
        private int cur_position;




        public mDirectorioAdapter(Context context, Cursor c, int layout) {
            super(context, c, layout);
            this.layout = layout;
            this.inflater = LayoutInflater.from(context);


        }


        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View v = inflater.inflate(layout, parent, false);
            return v;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            mEvent c = new mEvent(cursor);

           // ImageView icono = (ImageView)view.findViewById(R.id.direccion_cell_hotel);
            TextView titulo = (TextView) view.findViewById(R.id.titulo_acomp);
            titulo.setText(c.titulo);







        }









    }





}
