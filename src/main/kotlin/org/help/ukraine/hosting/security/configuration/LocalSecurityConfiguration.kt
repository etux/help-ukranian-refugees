package org.help.ukraine.hosting.security.configuration

import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Profile("local")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
class LocalSecurityConfiguration : WebSecurityConfigurerAdapter(){

    override fun configure(webSecurity: WebSecurity) {
        webSecurity.ignoring().anyRequest()
    }
}