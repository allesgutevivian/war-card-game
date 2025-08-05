package GroupProject1_WarCardGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogicPart {

	public static void main(String[] args) {
		
		// the array of all the cards 所有牌的序列 모든 카드의 시퀀스
		String[] suits = {"Diamonds️","Spades️","Clubs️","Hearts️"};
		String[] number = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		List<Card> deck = new ArrayList<>();
		for (String suit : suits) {
            for (String rank : number) {
                deck.add(new Card(suit,rank));
            }
        }
		
		// shuffle the cards 洗牌 섞다
		Collections.shuffle(deck);
		// for(String t:deck) System.out.println(t); // test if the shuffle has been done 检查牌是否都洗好了 패가 잘 뒤섞였는지를 검사하다
		
		//create the two players card list 创建两个玩家的牌的序列 두 플레이어의 카드 순서를 만듭니다
		List<Card> user = new ArrayList<>();
		List<Card> computer = new ArrayList<>();
		
		// deal the cards 发牌 패 돌 릴
		for(int i=0;i<deck.size();i++) {
			if(i%2==0) user.add(deck.get(i));
			else computer.add(deck.get(i));
		}
		
		while (!user.isEmpty() && !computer.isEmpty()) {
		//**此段稍后需要替换成GUI** **이 섹션은 나중에 GUI로 바꿔야 한다**
		// 与GUI结合的方法就是：点击按钮就激发一次循环 ; while的条件语句换成if来判断，如果不满足就直接输出最终结果
		// gui는 버튼을 누르면 루프가 발동되고while의 조건문을 if로 바꾸어, if 가 만족되지 않으면 최종 결과를 출력한다
			List<Card> pile = new ArrayList<>();
            boolean war = playRound(user, computer, pile);

            //**此段稍后需要替换成GUI** **이 섹션은 나중에 GUI로 바꿔야 한다**
            System.out.println("User has " + user.size() + " cards.");
            System.out.println("Computer has " + computer.size() + " cards.");
            System.out.println("--------------------------------------------------------");

            if (war && (user.size() < 4 || computer.size() < 4)) {
                System.out.println("One player does not have enough cards for WAR. Game Over.");
                break;
            }
		}
		
		// 判定最终胜负 최종 승부를 판정하다
		//**此段稍后需要替换成GUI** **이 섹션은 나중에 GUI로 바꿔야 한다**
        if (user.size() > computer.size()) System.out.println("User wins!");
        else if (user.size() < computer.size()) System.out.println("Computer wins!");
        else System.out.println("It's a tie!");
		
		
	}

	// 判断每一场小比赛的胜负 모든 작은 경기를 판단하라
	public static boolean playRound(List<Card> user, List<Card> computer, List<Card> pile ) { // pile记录的是累计押注牌的数量 pile은 누적 베팅판의 수를 기록한다
		 if (user.isEmpty() || computer.isEmpty()) return false; //如果两方任意一方没有牌了，则结束比赛 양쪽 중 어느 한 쪽이라도 패가 없으면 시합을 끝냅니다
		 
		 Card u = user.get(0);
		 Card c = computer.get(0); //抽出本轮参与比较的牌 이번 라운드에서 비교할 패를 뽑는다
		 
		 pile.add(u);
		 pile.add(c); //把本轮参与比较的两张牌也加入押注牌区 이번 라운드에서 비교에 참가한 두 장의 카드도 도박장에 넣는다
		 
		 //**此段稍后需要替换成GUI** **이 섹션은 나중에 GUI로 바꿔야 한다**
		 System.out.println("User: "+u.toString());
		 System.out.println("Computer: "+c.toString()); //打印本轮参与比较的两张牌 이번 라운드 비교 카드 두 장을 인쇄합니다
		 
		 int u1=u.getNumber();
		 int c1=c.getNumber(); //把参与比较的牌转换成数字值 비교에 참여하는 패를 숫자 값으로 바꾼다
		 
		 user.remove(0);
		 computer.remove(0); //把最上面那张牌拿掉 맨 위의 카드를 빼시오
		 
		 if(u1>c1) { //如果用户赢了 사용자가 이기면 말이죠
			 user.addAll(pile); //把押注堆里所有的牌都给user 도박장 안의 모든 패를 user에게 준다
			 System.out.println("User Win!"); //**此段稍后需要替换成GUI** **이 섹션은 나중에 GUI로 바꿔야 한다**
			 pile.clear(); //清空押注堆 베팅 블록을 비우다
			 return false;
		 }
		 else if(u1<c1) { //如果用户输了 사용자가 패배할 경우
			 computer.addAll(pile); //把押注堆里所有的牌都给computer 도박장 안의 모든 패를 computer 에게 준다
			 System.out.println("Computer Win!"); //**此段稍后需要替换成GUI** **이 섹션은 나중에 GUI로 바꿔야 한다**
			 pile.clear(); //清空押注堆 베팅 블록을 비우다
			 return false;
		 }
		 else { //进入war状态 전쟁 상태에 들어가다
			 System.out.println("War"); //**此段稍后需要替换成GUI** **이 섹션은 나중에 GUI로 바꿔야 한다**
			 if (user.size() < 4 || computer.size() < 4) return true;
			 for (int i = 0; i < 3; i++) {
	                pile.add(user.remove(0));
	                pile.add(computer.remove(0));
			 }
			 return playRound(user,computer,pile);
		 }
		 
	}
}
