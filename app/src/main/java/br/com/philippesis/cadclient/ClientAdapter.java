package br.com.philippesis.cadclient;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.philippesis.cadclient.domain.entities.Client;
import br.com.philippesis.cadclient.domain.repositories.ClientRepository;
import br.com.philippesis.cadclient.utils.GetConnection;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolderClient> {

    private List<Client> mDataClients;
    private ClientRepository clientRepository;
    private GetConnection getConnection;

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
        int retorno = 0;
        try {
            if(mDataClients != null || !mDataClients.isEmpty()) {
                retorno = mDataClients.size();
            }
        } catch (Exception e) {
            Log.e("Err-Cadapter: ", e.toString());
        }
        return retorno;
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
                        intent.putExtra("type", 2);
                        ((AppCompatActivity) context).startActivityForResult(intent, 1);

                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final Client client = mDataClients.get(getLayoutPosition());
                    BaseActivity.genericAlert(context, "Detalhes", "Client: "+client.getmName()+"\nEndereço: "+client.getmAddress()+"\nEmail: "+
                            client.getmEmail()+"\nTelefone: "+client.getmPhone(), "OK");
                    return true;
                }
            });

        }
    }

}
