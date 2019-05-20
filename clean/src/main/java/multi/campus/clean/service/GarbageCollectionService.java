package multi.campus.clean.service;

import multi.campus.clean.domain.GarbageCollection;
import multi.campus.clean.domain.PageInfo;
import multi.campus.clean.domain.User;

public interface GarbageCollectionService {
	PageInfo<GarbageCollection> getPage(int page) throws Exception;
	
	// 사용자별 수집 데이터 조회
	User getUserCollection(String userid) throws Exception;
	
	// 수집 데이터 생성
	int create(GarbageCollection collection) throws Exception;
	
	// 수집 데이터 업데이트
	int update(int collectionNo) throws Exception;
	
	// 수집 데이터 삭제
	boolean delete(int collectionNo) throws Exception;
}
