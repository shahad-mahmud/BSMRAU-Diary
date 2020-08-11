package com.teamhalum.shahad.bsmrudiary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    Context context;
    List<Contacts> contactsList;

    public ContactsAdapter(Context context, List<Contacts> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.contacts_layout, viewGroup, false);
        ContactViewHolder holder = new ContactViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, final int i) {
        Contacts contacts = contactsList.get(i);

        contactViewHolder.post.setText(contacts.getPost());
        contactViewHolder.name.setText(contacts.getName());

        contactViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,"Contact Details", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ContactsDetail.class);

                intent.putExtra("name", contactsList.get(i).getName());
                intent.putExtra("post", contactsList.get(i).getPost());
                intent.putExtra("email", contactsList.get(i).getEmail());
                intent.putExtra("resNum", contactsList.get(i).getRes_num());
                intent.putExtra("officeNum", contactsList.get(i).getOffice_num());
                intent.putExtra("mobNum1", contactsList.get(i).getMobile_num1());
                intent.putExtra("mobNum2", contactsList.get(i).getMobile_num2());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView post;
        RelativeLayout layout;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameId);
            post = itemView.findViewById(R.id.postID);
            layout = itemView.findViewById(R.id.ContactLayout);
        }
    }

}
