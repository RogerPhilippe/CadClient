package br.com.philippesis.cadclient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.philippesis.cadclient.domain.entities.Client;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolderClient> {

    private List<Client> mDataClients;

    public ClientAdapter(List<Client> data) {
        this.mDataClients = data;
    }

    @Override
    public ViewHolderClient onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.line_client_recycleview, parent, false);

        ViewHolderClient viewHolderClient = new ViewHolderClient(view, parent.getContext());

        return viewHolderClient;
    }

    @Override
    public void onBindViewHolder(ViewHolderClient viewHolderClient, int position) {

        if(mDataClients != null && mDataClients.size() > 0) {

            viewHolderClient.txtTitleLineRecycleview.setText(mDataClients.get(position).getmName());
            viewHolderClient.txtSubtitleLineRecycleview.setText(mDataClients.get(position).getmPhone());

        }

    }

    @Override
    public int getItemCount() {
        return mDataClients.size();
    }

    protected class ViewHolderClient extends RecyclerView.ViewHolder {

        protected TextView txtTitleLineRecycleview;
        protected TextView txtSubtitleLineRecycleview;

        public ViewHolderClient(View itemView, final Context context) {
            super(itemView);

            txtTitleLineRecycleview = (TextView) itemView.findViewById(R.id.idtxtTitleLineRecycleview);
            txtSubtitleLineRecycleview = (TextView) itemView.findViewById(R.id.idtxtSubtitleLineRecycleview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Capturar objeto selecionado no Recycleview
                    if(mDataClients.size() > 0) {

                        Client client = mDataClients.get(getLayoutPosition());

                        Intent intent = new Intent(context, CadClienteActivity.class);
                        intent.putExtra("client", client);
                        ((AppCompatActivity) context).startActivityForResult(intent, 1);

                    }

                }
            });

        }
    }

}
