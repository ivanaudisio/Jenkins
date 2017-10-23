import hudson.*
import hudson.model.*

// Every Jenkins instance can decrypt only their own secrets

// Encrypt Secret
  
pw='MyPassword1234'
passwd_enc = hudson.util.Secret.fromString(pw).getEncryptedValue()
println(passwd_enc)

// Decrypt Secret
  
hashed_pw = passwd_enc
passwd = hudson.util.Secret.decrypt(hashed_pw)
println(passwd)
