package model;

public class SplitPage {
    //��ҳ����ʱ,�����ʶ����
    final public static String FIRSTPAGE="first";//�����һҳ
    final public static String PREVIOUSEPAGE="previous";//������һҳ
    final public static String NEXTPAGE="next";//������һҳ
    final public static String LASTPAGE="last";//�������һҳ
    
    private int pageRows=20;//ÿҳ��ʾ��¼��,Ĭ��3��,������ҳ������
    private int totalRows=0;//�ܵļ�¼��,������������ݿ�dao�����ṩ
    private int currentPage=1;//��ǰ��ʾ��ҳ����,Ĭ�ϵ�һҳ
    private int firstPage=1;//��ҳλ��,Ĭ�ϵ�һҳ
    private int totalPages=1;//�ܵ�ҳ������,Ĭ�Ͼ�һҳ
    
    
    public int getCurrentPage() {
           return currentPage;
    }
    public void setCurrentPage(int currentPage) {
           this.currentPage = currentPage;
    }
    
    public int getFirstPage() {
           return firstPage;
    }
    public void setFirstPage(int firstPage) {
           this.firstPage = firstPage;
    }
    
    public int getPageRows() {
           return pageRows;
    }
    public void setPageRows(int pageRows) {
           if(pageRows==0)throw new ArithmeticException();
           this.pageRows = pageRows;//���pageRows������Ϊ��,Ӧ���׳��쳣.
           //�޸�ÿҳ��ʾ��¼��,����ֱ��Ӱ����ҳ����,����Ҫͬʱ�޸�
           this.totalPages=(this.totalRows%this.pageRows==0)?this.totalRows/this.pageRows:this.totalRows/this.pageRows+1;
    }     
    public int getTotalRows() {
           return totalRows;
    }     
    //���÷�ҳ������ܼ�¼���Ժ�,��Ӧ�ø���ÿҳ����ʾ��¼��,����õ��ܵ�ҳ����
    public void setTotalRows(int totalRows) {
           this.totalRows = totalRows;
     //�����ܵ�ҳ����(������ҳ���),���������������պó���,ֵ������������,�������������,��Ӧ������1.
           this.totalPages=(this.totalRows%this.pageRows==0)?this.totalRows/this.pageRows:this.totalRows/this.pageRows+1;
    }
    //��Ӧ���ṩ����������ҳ����,���Ǽ���õ���
    //��Ӧ���ṩ��ȡ��ҳ�����ķ���.
    public int getTotalPages() {
           return totalPages;
    }
    
    //��������ҳ��ı�ʶ����,���¼��㵱ǰҪ��ʾ��ҳ��
    //���ķ���,ʵ�ַ�ҳ��ʾ����.
    public int confirmPage(String flag){
           int newPage=this.currentPage;
           if((flag!=null)&&(!flag.equals(""))){//flagֻ����������ֵ֮һ
                  if(flag.equals(SplitPage.FIRSTPAGE)){
                         newPage=1;
                  }else if(flag.equals(SplitPage.LASTPAGE)){
                         newPage=this.totalPages;
                  }else if(flag.equals(SplitPage.NEXTPAGE)){
                         //ҳ�������͵�ǰҳ���������,�������ôҳ���Ų�������,����ҳ���ż�һ
                         newPage=(this.totalPages==this.currentPage)?this.currentPage:this.currentPage+1;
                         //System.out.println(newPage);
                  }else if(flag.equals(SplitPage.PREVIOUSEPAGE)){
                         //��һ��ҳ��͵�ǰҳ�������,�������ôҳ���Ų���ǰ��,����ҳ���ż�һ
                         newPage=(this.firstPage==this.currentPage)?this.currentPage:this.currentPage-1;
                  }else{//������һ�������ַ���
                         int tpage=Integer.parseInt(flag.trim());
                         newPage=(this.totalPages <= tpage)? this.totalPages:tpage;
                  }
           }else{//��������ʶ����Ϊ��,��ô��ǰҳ�벻��
                  newPage=this.currentPage;
           }
           //�ڷ���ǰ���õ�ǰҳ��
           this.setCurrentPage(newPage);
           return newPage;
    }
}
