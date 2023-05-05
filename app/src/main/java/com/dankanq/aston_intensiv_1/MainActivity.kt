package com.dankanq.aston_intensiv_1

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val user = User(name = DEFAULT_USER_NAME)
            .also {
                if (it.name == DEFAULT_USER_NAME) editUserName(it)
            }

        user.name?.let {
            val action = createAction(user)
            doAction(action)

            with(textView) {
                text = user.name
                setOnClickListener {
                   Toast.makeText(
                       this@MainActivity,
                       user.toString(),
                       Toast.LENGTH_LONG
                   ).show()
                }
            }
        }
    }

    private fun editUserName(user: User) {
        user.apply {
            this.name = getString(R.string.user)
        }
    }

    private fun createAction(user: User): Action {
        return user.run {
            Action.ShowMessage(
                "${user.name}"
            )
        }
    }

    private fun doAction(action: Action) {
        when (action) {
            is Action.ShowMessage -> {
                Toast.makeText(
                    this@MainActivity,
                    action.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    companion object {
        private const val DEFAULT_USER_NAME = "Undefined"
    }
}