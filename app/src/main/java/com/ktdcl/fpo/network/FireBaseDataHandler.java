package com.ktdcl.fpo.network;


public class FireBaseDataHandler {
   // private DatabaseReference databaseReference;

    public FireBaseDataHandler() {
    }
  /*  public DataSnapshot getNews(String path)
    {
        databaseReference = KtdclApplication.getFireBaseRef();
        String lang = SharedPrefsHelper.getInstance().get(LANGUAGE, "ka");

        databaseReference.child("path").child(lang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {

                   *//* try {
                        NewsModel model = snapshot.getValue(NewsModel.class);
                        newsModelList.add(model);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }*//*
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return null;
    }*/
}
