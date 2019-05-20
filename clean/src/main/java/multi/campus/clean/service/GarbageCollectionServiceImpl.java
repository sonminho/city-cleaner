package multi.campus.clean.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import multi.campus.clean.dao.GarbageCollectionDao;
import multi.campus.clean.domain.GarbageCollection;
import multi.campus.clean.domain.PageInfo;
import multi.campus.clean.domain.User;

@Repository
public class GarbageCollectionServiceImpl implements GarbageCollectionService {
	@Autowired
	GarbageCollectionDao dao;
	
	static final int PER_PAGE_COUNT = 10;
	
	@Override
	public PageInfo<GarbageCollection> getPage(int page) throws Exception {
		int start = (page-1) * PER_PAGE_COUNT;
		int end = start + PER_PAGE_COUNT;
		
		int totalCount = dao.count();
		List<GarbageCollection> list = dao.getPage(start, end);
				
		return new PageInfo<>(totalCount, (int)Math.ceil(totalCount / (double)PER_PAGE_COUNT), page, PER_PAGE_COUNT, list);
	}

	@Override
	public User getUserCollection(String userid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(GarbageCollection collection) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(int collectionNo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(int collectionNo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
