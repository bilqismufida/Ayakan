    package com.method.ayakan.repository;

    import com.method.ayakan.model.Catatan;

    public class CatatanRepository extends BaseRepository<Catatan>{
        
        @Override
        public void save(Catatan link){
            db.put(link.getId(), link);
        }
    }
