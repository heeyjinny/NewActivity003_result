package com.heeyjinny.newactivity003

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heeyjinny.newactivity003.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    //뷰바인딩
    val binding by lazy { ActivitySubBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //뷰바인딩
        setContentView(binding.root)

        //서브 액티비티 종료 버튼을 누를 떄 자신을 호출했던 메인 액티비티로 값을 돌려주고 액티비티 종료
        binding.btnClose.setOnClickListener {

            //호출한 메인 액티비티에 돌려줄 인텐트 생성 후 변수 resultIntent 에 저장
            //인텐트를 돌려줄 떄는 대상을 지정하지 않아도 되므로 Intent()안에 아무것도 담지 않음
            val resultIntent = Intent()

            //에디트텍스트뷰의 아이디 editMessage에 입력받은 (.text.)텍스트 값을
            //toString()하여 텍스트(.text.)로 받아와서
            //변수 resultIntent에 값 저장(putExtra())
            resultIntent.putExtra("returnValue",binding.editMessage.text.toString())

            //호출한 측으로 변수 resultIntent에 저장된 값과 상태값 전달
            //호출한 측으로 전달하는 메서드 : setResult(상태값, 전달할 인텐트)
            //상태값 : RESULT_OK, RESULT_CANCELED 상수로 정의되어 있음
            //처리한 결과값에 따라 성공이면 OK, 취소되었으면 CANCELED 사용
            setResult(RESULT_OK, resultIntent)

            //액티비티 종료 : finish()
            finish()

        }

    }//onCreate
}//SubActivity