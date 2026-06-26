package com.method.ayakan.repository;

import com.method.ayakan.model.Link;

public class LinkRepository extends BaseRepository<Link> {
    
    @Override
    public void save(Link link) {
        db.put(link.getId(), link);
    }
}