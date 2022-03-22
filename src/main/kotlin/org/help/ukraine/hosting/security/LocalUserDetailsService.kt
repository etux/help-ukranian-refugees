package org.help.ukraine.hosting.security

import org.springframework.context.annotation.Profile
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
@Profile("local")
class LocalUserDetailsService: UserDetailsService
{
    private val users: MutableMap<String, UserDetails> = mutableMapOf()

    init {
        defaultLocalUser().also { users += it.username to it }
    }

    override fun loadUserByUsername(username: String?): UserDetails? {
        return username?.let { users[it] }
    }

    companion object {
        fun defaultLocalUser() = object : UserDetails {
            override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
                return mutableListOf(
                    object : GrantedAuthority {
                        override fun getAuthority() = "ROLE_USER"
                    }
                )
            }

            override fun getPassword() = "password"

            override fun getUsername() = "username"

            override fun isAccountNonExpired() = true

            override fun isAccountNonLocked() = true

            override fun isCredentialsNonExpired() = true

            override fun isEnabled() = true

        }
    }
}