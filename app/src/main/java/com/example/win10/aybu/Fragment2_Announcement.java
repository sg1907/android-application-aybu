package com.example.win10.aybu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint("ValidFragment")
public class Fragment2_Announcement extends Fragment {

    private final String TAG = "ANNOUNCEMENT";


    private ListView lv;
    public ArrayList arraylist_announcement = new ArrayList();

    public ArrayList arrayList_Links = new ArrayList();

    private ArrayAdapter<String> adapter;

    //private static String URL = "http://ybu.edu.tr/muhendislik/bilgisayar/";


    private static String URL;


    private List<String> Muh_URLs = new ArrayList<String>();

    int contIndex;

    @SuppressLint("ValidFragment")
    public Fragment2_Announcement(int controllerIndexxx) {
        this.contIndex= controllerIndexxx;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2_announcement_layout,container,false);

        lv = (ListView) view.findViewById(R.id.listView_Announcement);


        String [] muh_urls;

        muh_urls = getResources().getStringArray(R.array.muh_urls);

        for(int i=0; i<muh_urls.length; i++)
            Muh_URLs.add(muh_urls[i]);

        //LoginActivity la = new LoginActivity();
        //MainActivity ma = new MainActivity();

        //Log.d(TAG,"controlllllll fragmentte mainden gelen: " + contIndex);

        //Log.d(TAG,"controlllllll fragmentte : " + la.control);

        //Toast.makeText(getActivity(),Muh_URLs.get(la.control), Toast.LENGTH_SHORT).show();

       //Toast.makeText(getActivity()," control : "+la.control, Toast.LENGTH_SHORT).show();

        URL = Muh_URLs.get(contIndex);

        new Fragment2_Announcement.GetAnnouncement().execute();
        adapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,arraylist_announcement);

        //lv.setAdapter(adapter);




        return view;
    }



    private class GetAnnouncement extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            //LoginActivity.control = 0;

            arraylist_announcement.clear();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Document doc = Jsoup.connect(URL).timeout(30*1000).get();

                Element title = doc.select("div.caContent").first();

                Iterator<Element> ite = title.select("div.cncItem").iterator();

                Iterator<Element> ite2 = title.select("div.caButton").iterator();

                //ite.next();


                while (ite.hasNext()){
                    Element div = ite.next();

                    arraylist_announcement.add((div.text()));
                    arrayList_Links.add(div.select("a").attr("href"));
                }

                while(ite2.hasNext()){
                    Element div = ite2.next();

                    arraylist_announcement.add((div.text()));
                    arrayList_Links.add(div.select("a").attr("href"));
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ArrayList<String> a = new ArrayList<String>();

                    for(int i=0; i<arrayList_Links.size(); i++){
                        //a.add("http://www.ybu.edu.tr/muhendislik/bilgisayar/" +arrayList_Links.get(i).toString());

                        a.add(URL +arrayList_Links.get(i).toString());
                    }

                    Uri uri = Uri.parse(a.get(position));
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            });


        }


    }
}


