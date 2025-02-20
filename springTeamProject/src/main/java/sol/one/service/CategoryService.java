package sol.one.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sol.one.VO.DetailVO;
import sol.one.VO.ProductVO;

@Service
public interface CategoryService {
	
	public List<ProductVO> list_all_category()throws Exception;
	
	public List<ProductVO> list_category(int category_id) throws Exception;

	public int count_all()throws Exception;
	
	public int count_category(int category_id)throws Exception;
	
	public List<DetailVO> detail(int product_id) throws Exception;
	
}
