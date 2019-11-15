package com.monsh.pokeapp;

import android.content.Context;
import android.content.Intent;
import com.google.android.material.snackbar.Snackbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.monsh.pokeapp.interfase.itemClicklistener;
import com.monsh.pokeapp.models.Pokemon;

import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {
    private ArrayList<Pokemon> dataset;
    private Context context;
    private Pokemon pokemon;





    public ListaPokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }


    @Override
    public ListaPokemonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaPokemonAdapter.ViewHolder holder, int position) {
        pokemon = dataset.get(position);
        /*holder.nombreTextView.setText(pokemon.getName());
        holder.itemClicklistener(new itemClicklistener(){

            @Override
            public void onclick(View view, int position) {
                Toast.makeText(context,"toco en pokemon: "+dataset.get(position).getName(),Toast.LENGTH_LONG).show();
            }
        });*/
        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);




    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }



    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();



    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView fotoImageView;
        private TextView nombreTextView;
        private CardView tarjetas;
        itemClicklistener itemClicklistener;


        public ViewHolder(View itemView) {
            super(itemView);

            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            tarjetas = (CardView) itemView.findViewById(R.id.tarjetas);

            tarjetas.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }
        public void onClickkk(View view){
            itemClicklistener.onclick(view,getAdapterPosition());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tarjetas:
                    String pok =  pokemon.getName();
                    Intent i = new Intent(v.getContext(),BulbasaurActivity.class);
                    v.getContext().startActivity(i);
                    Snackbar.make(v, pok, Snackbar.LENGTH_SHORT).show();
                    break;
            }

        }

        public void itemClicklistener(itemClicklistener itemClicklistener) {
            this.itemClicklistener = itemClicklistener;
        }
    }



}
