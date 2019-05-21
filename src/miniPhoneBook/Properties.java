package miniPhoneBook;

public class Properties {
	
	static String menu_EX1 = "====== 미니 전화번호부 ======\n"
			+ "1. 전화번호부 전체보기\n"
			+ "2. 전화번호부 입력하기\n"
			+ "3. 전화번호부 지우기\n"
			+ "4. 전화번호부 수정하기\n"
			+ "5. 전화번호부 종료하기\n"
			+"=========================\n";
	
	static String menu2_name = "===== 전화번호 입력모드 =====\n"
											+ "이름 입력 후 엔터를 누르세요\n ";
	static String menu2_phone = "전화번호 입력 후 엔터를 누르세요\n ";
	static String menu2_add = "입력이 완료되었습니다\n "
											+ "메뉴를 선택하세요\n ";
	static String menu2_duplicate = "동일한 이름과 전화번호가 존재합니다\n "
												+ "입력에 실패하였습니다\n "
												+ "메뉴를 선택하세요\n ";
	static String menu2_duplicate2 = "전화번호는 다르지만 "
												+ "같은 이름이 존재합니다\n "
												+ "그래도 입력하시겠습니까? (y/n)\n ";
	static String menu_no = "작업을 취소하였습니다\n "
									+ "메뉴를 선택하세요\n ";
	
	static String menu3_name = "삭제할 이름을 입력 하세요.\n";
	static String menu3_phone = "검색 완료!!!\n"
											+ "삭제할 전화번호를 입력 하세요.\n";
	static String menu3_check = "정말로 삭제를 하시겠습니까?(y/n)\n";
	static String menu3_result = "삭제가 완료되었습니다.\n"
											+ "메뉴를 선택하세요.\n";
	
	static String menu_nodata = "일치하는 정보가 없습니다.\n"
											+ "메뉴를 선택하세요\n ";
	
	static String menu4 = "조회할 이름을 입력 하세요.\n";
	static String menu4_phone = "조회할 전화번호를 입력 하세요.\n";
	
	static String menu4_check = "위 전화번호를 수정 하시겠습니까?(y/n)\n";
	static String menu4_modi1 = "아래 작성방법에 맞게 수정할 \n"
											+ "이름과 전화번호를 입력해 주세요.\n"
											+"\n"
											+ "<<작성 방법>>\n"
											+ "홍길동,1234567890\n"
											+ "\n"
											+ "이름과 전화번호 사이 콤마(,)를 꼭!!\n"
											+ "입력해 주셔야 합니다.\n";
	
	static String menu4_ERR = "작성방법이 틀렸습니다.\n"
											+ "작업이 취소 되었습니다.\n"
											+ "메뉴를 선택하세요.\n";
	static String menu4_result = "수정이 완료되었습니다.\n"
											+ "메뉴를 선택하세요.\n";
	
	

	static String file_PATH = "C:/Users/rogem/Desktop/eclipse-workspace/workspace study/miniPhoneBook/src/miniPhoneBook/phonebook.txt";			//	집컴
	static String file_PATH2 = "C:/Users/rogem/Desktop/eclipse-workspace/workspace study/javaStudy/src/phoneBook/phoneBook2.txt";
	
//	static String file_PATH = "C:/Users/ju/Desktop/steveleejava_workspace/javaStudy/src/phoneBook/phonebook.txt";									// 노트북
//	static String file_PATH2 = "C:/Users/ju/Desktop/steveleejava_workspace/javaStudy/src/phoneBook/phonebook2.txt";
}
