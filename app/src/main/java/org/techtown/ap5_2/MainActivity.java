package org.techtown.ap5_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random(); // 랜덤 기능을 쓰기 위한 선언. 이렇게 해놓은 뒤에는 rand. 뒤에 여러 메소드들을 쓸 수 있음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int count = 0;
    long time_start;

    public void bt_launch_onClick(View v) { // 버튼을 누르면 FrameLayout 안에 새로운 버튼이 생기게
        Button bt_launch = findViewById(R.id.bt_launch);
        FrameLayout layout = findViewById(R.id.layout);
        Button bt = new Button(this); // 새 버튼 인스턴스가 bt에 담김 // context 글자는, this가 context 인수 자리에 들어갈 것이라는 의미 ..

        bt.setText("" + count);

        // FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT ); -> 너비, 높이 모두 wrap_content
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(150, 150); // 너비 150, 높이 150. 이렇게 새로운 LayoutParams 인스턴스를 만들어서 params에 담음

        // params.leftMargin = 50 * count;
        // params.topMargin = 50 * count; -> 매번 추가할 때마다 margin값이 달라지게. 50만 한다면 모든 버튼들이 같은 자리에 위치하지만 count값을 곱해줌으로써 margin이 달라짐

        // params.leftMargin = rand.nextInt(600); -> 임의의 값이 되도록 지정. 괄호 안에는 최대 얼마까지 할지를 적음. 3을 적는다면, 0 1 2가 나올 수 있음
        // params.topMargin = rand.nextInt(600); -> 0 ~ 599

        params.leftMargin = rand.nextInt(layout.getMeasuredWidth() - 150); // (Framelayout의 현 시점의 너비 - 버튼 너비) 만큼의 범위에서 랜덤으로 돌림 -> Framelayout 화면 전체 내에서 랜덤으로
        params.topMargin = rand.nextInt(layout.getMeasuredHeight() - 150); // (Framelayout의 현 시점의 높이 - 버튼 높이) 만큼의 범위에서 랜덤으로 돌림

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 첫 번째 인수 자리에 onClick당한 view를 담아서 호출하도록 되어있음
                Button bt_launch = findViewById(R.id.bt_launch);
                TextView tv = findViewById(R.id.tv);

                long time_elapsed = System.currentTimeMillis() - time_start; // 현재시각 - 발사버튼 누른 시간
                tv.setText(time_elapsed + "ms 걸렸습니다."); // 버튼 옆 텍스트가 이렇게 변함

                view.setVisibility(View.GONE); // 지금 누른, 이 이벤트 핸들러 실행을 야기한 버튼을 화면에서 지우기
                bt_launch.setVisibility(View.VISIBLE); // FrameLayout에 생긴 버튼을 누르면, 그 버튼은 사라지고 발사버튼이 다시 보임
            }
        });

        layout.addView(bt, params); // 괄호 안에 내용 -> 무슨 View를 추가할건지

        count = count + 1; // 버튼을 누를 때마다 새로 생기는 버튼에 적힌 숫자가 +1됨
        // 같은 버튼에 숫자만 +1되는게 아니라 버튼이 계속 새로 생기는 것. 누를 때마다 bt_launch_onCLick 함수가 실행되기 때문

        time_start = System.currentTimeMillis(); // 현재 시각과 관련한 ms 단위의 숫자가 long 형식으로 나옴. 이것을 time_start에 담음

        bt_launch.setVisibility(View.INVISIBLE); // 어떻게 보여줄지 // Invisible을 하면 화면에서 가려짐. 사용자와의 상호작용 차단의 느낌이라 클릭도 안됨
        // Invisible은 화면에서 가려지지만 자리는 차지하고 있음
        // Gone은 아예 없어져서 차지하는 자리까지 없어짐 -> 다른 view들의 위치까지 바뀔 수 있음
    }
}