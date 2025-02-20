package sol.one.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import sol.one.VO.BoardVO;
import sol.one.VO.CommentVO;
import sol.one.VO.LikeVO;
import sol.one.VO.MemberVO;
import sol.one.VO.ReportVO;
import sol.one.VO.T_tradeVO;
import sol.one.VO.TradelistVO;


public interface MypageMapper {
	
	public MemberVO login(MemberVO mem)throws Exception;
	
	public MemberVO list_user_mypage(Long user_id) throws Exception;

	public void update_info_mypage(MemberVO mem) throws Exception;
	
	public void delete_info_mypage(Long user_id)throws Exception;
	
	public void go_report(ReportVO report)throws Exception;
	
	public List<LikeVO> list_like_mypage(Long user_id) throws Exception;

	public List<TradelistVO> list_trade_mypage(@Param("user_id")Long user_id) throws Exception;

	public List<BoardVO> list_board_mypage(Long user_id) throws Exception;

	public List<CommentVO> list_comment_mypage(Long user_id) throws Exception;

	public List<ReportVO> list_report_mypage(Long user_id) throws Exception;

	public int check_password_mypage(MemberVO mem) throws Exception;


	
}
