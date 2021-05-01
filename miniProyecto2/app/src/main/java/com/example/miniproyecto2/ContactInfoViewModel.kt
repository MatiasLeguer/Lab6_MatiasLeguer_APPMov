package com.example.miniproyecto2

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader

class ContactInfoViewModel(application: Application): AndroidViewModel(application){
    private val contactList :MutableList<InfoContact> = mutableListOf<InfoContact>()
    val createdContact = MutableLiveData<InfoContact>()
    val myContact = MutableLiveData<MutableList<InfoContact>>()
    val filename = "contact_info.txt"
    lateinit var navigator: Navigator



    init{
        try {

            val fileObj = application.baseContext.openFileInput(filename)
            val fileReader = InputStreamReader(fileObj)
            var text: String? = null
            while ({text = BufferedReader(fileReader).readLine();text}()!= null){

                if(text != null){
                    val result = text!!.split(";")!!.toTypedArray()
                    contactList.add(InfoContact(name = result[0], phoneNumber = result[1]))
                }
            }
        }
        catch (e: Exception){
            Log.e("FileNotFound", "[!]ERROR ~File not found")
        }
        this.myContact.postValue(contactList)

    }

    fun setNavigator(activity: MainActivity?){
        navigator = Navigator(activity)
    }

    fun addContact(contact: InfoContact){
        contactList.add(contact)
        this.myContact.postValue(contactList)
    }

    fun createContact(item: InfoContact){
        createdContact.postValue(item)
    }

    fun writeText(){
        var application: Application = getApplication<Application>().applicationContext as Application
        var fileWriter = application.baseContext.openFileOutput(filename, Context.MODE_PRIVATE)

        for (i in contactList){
            fileWriter.write((i.name+";"+i.phoneNumber+"\n").toByteArray())
        }
        fileWriter.close()

    }
}