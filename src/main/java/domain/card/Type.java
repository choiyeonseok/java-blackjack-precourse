package domain.card;

public enum Type {
	SPADE("�����̵�"), 
	DIAMOND("���̾Ƹ��"), 
	HEART("��Ʈ"), 
	CLUB("Ŭ�ι�");

	private String koreanType;

	Type(String koreanType) {
		this.koreanType = koreanType;
	}

	public String getKoreanType() {
		return koreanType;
	}

}
