package multi.campus.clean.service;

import multi.campus.clean.domain.PageInfo;
import multi.campus.clean.domain.User;

public interface UserService {
	PageInfo<User> getPage(int page) throws Exception;
	
	// 사용자 계정 조회
	User getUser(String userid) throws Exception;
	
	// 사용자 계정 생성
	int create(User user) throws Exception;
	
	// 사용자 계정 업데이트
	int update(User user) throws Exception;
	
	// 사용자 계정 삭제
	boolean delete(User user) throws Exception;
	
}
