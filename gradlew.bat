<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.example.leonardodruid.uailistar">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".AberturaActivity"></activity>

        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="@string/facebook_app_id"
            tools:replace="android:value" />

        <activity android:name=".RecuperarSenha_Activity" />
        <activity
            android:name=".PrincipalUaiListar_Activity"
            android:label="@string/title_activity_principal_uai_listar_"
            android:parentActivityName=".AberturaActivity"
            android:theme="@style/AppTheme.NoActionBar">
            <meta-data
                android:name="android.support.PARENT_ACTIVITY"
                android:value="com.example.leonardodruid.uailistar.Login_Email" />
        </activity>
        <activity android:name=".Cadastrar_Email" />
        <activity android:name=".Login_Email">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       