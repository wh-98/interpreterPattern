package interpreterPattern;
import java.util.*;
//�ý�����ģʽ���һ�����ػ�ͨ�����������Ķ���������
//˵�������硰�ػ�ͨ�������������������жϳ˿͵���ݣ�����ǡ��عء����ߡ����ݡ��ġ����ˡ� ����Ů������ͯ���Ϳ�����ѳ˳���������Ա�˳�һ�ο� 2 Ԫ��
/*�ķ�����
  <expression> ::= <city>��<person>
  <city> ::= �ع�|����
  <person> ::= ����|��Ů|��ͯ
*/
public class InterpreterPatternDemo
{
    public static void main(String[] args)
    {
        Context bus=new Context();
        bus.freeRide("�عص�����");
        bus.freeRide("�عص�������");
        bus.freeRide("���ݵĸ�Ů");
        bus.freeRide("���ݵĶ�ͯ");
        bus.freeRide("ɽ���Ķ�ͯ");
    }
}
//������ʽ��
interface Expression
{
    public boolean interpret(String info);
}
//�ս�����ʽ��
class TerminalExpression implements Expression
{
    private Set<String> set= new HashSet<String>();
    public TerminalExpression(String[] data)
    {
        for(int i=0;i<data.length;i++)set.add(data[i]);
    }
    public boolean interpret(String info)
    {
        if(set.contains(info))
        {
            return true;
        }
        return false;
    }
}
//���ս�����ʽ��
class AndExpression implements Expression
{
    private Expression city=null;    
    private Expression person=null;
    public AndExpression(Expression city,Expression person)
    {
        this.city=city;
        this.person=person;
    }
    public boolean interpret(String info)
    {
        String s[]=info.split("��");       
        return city.interpret(s[0])&&person.interpret(s[1]);
    }
}
//������
class Context
{
    private String[] citys={"�ع�","����"};
    private String[] persons={"����","��Ů","��ͯ"};
    private Expression cityPerson;
    public Context()
    {
        Expression city=new TerminalExpression(citys);
        Expression person=new TerminalExpression(persons);
        cityPerson=new AndExpression(city,person);
    }
    public void freeRide(String info)
    {
        boolean ok=cityPerson.interpret(info);
        if(ok) System.out.println("����"+info+"�������γ˳���ѣ�");
        else System.out.println(info+"�������������Ա�����γ˳��۷�2Ԫ��");   
    }
}