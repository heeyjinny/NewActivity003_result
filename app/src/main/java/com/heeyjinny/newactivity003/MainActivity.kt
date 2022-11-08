package com.heeyjinny.newactivity003

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.heeyjinny.newactivity003.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //뷰바인딩
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //뷰바인딩
        setContentView(binding.root)

        //인텐트 생성
        val intent = Intent(this, SubActivity::class.java)

        //서브액티비티 값 돌려받기
        //기존 onActivityResult()는 안정성 문제로 2020년 10월 부터 deprecated목록 추가
        //ActivityResultContracts 방식 사용

        //서브 액티비티에서 돌려준 값을 activityResult변수에 저장
        //registerForActivityResult(){} 메서드 작성
        //ActivityResultContracts 방식을 사용한 액티비티에서 받은 값 받아오기
        //ActivityResultContracts.StartActivityForResult()
        //참고 URI : https://developer.android.com/reference/kotlin/androidx/activity/result/contract/ActivityResultContracts
        val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

            //서브액티비티에서 돌려받은 resultCode가 정상인지 체크
            if (it.resultCode == RESULT_OK){
                //정상이라면 돌려받은 인텐트에서 키 값을 사용해서 메시지를 꺼내(it.data?) 변수에 저장 getStringExtra("키 값")
                val message = it.data?.getStringExtra("returnValue")

                //토스트 출력
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                //로그 출력
                Log.d("message","$message")
                //텍스트 뷰 변경
                binding.resultView.text = message

            }

        }

        //버튼을 클릭하면 인텐트 실행, 서브액티비티 실행
        //btnStart.setOnClickListener { startActivity(intent) }
        //startActivity()메서드로 실행된 액티비티에서는 값을 돌려받을 수 없음

        //값을 돌려받고 싶을 때 activityResult변수의 launch()메서드를 사용해야 함
        binding.btnStart.setOnClickListener { activityResult.launch(intent) }


    }//onCreate
}//MainActivity