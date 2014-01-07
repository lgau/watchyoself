package com.bazaarvoice.portal.auth

import com.bazaarvoice.portal.Role
import com.bazaarvoice.portal.User
import com.bazaarvoice.portal.UserRole
import com.bazaarvoice.portalsdk.client.UserEntity
import portal.auth.PortalUser
import portal.auth.PortalUserProvider

class UserProvider implements PortalUserProvider {
    @Override
    PortalUser getUser(UserEntity userEntity) {
        String[] clientNameUsernamePair = userEntity.getUserId().split("/")
        def username = clientNameUsernamePair[1]
        User user = User.findByUsername(username)

        if (!user) {
            /**
             * We've never seen this user, but the Portal framework says they're allowed into our app, so create a
             * user object so that we can send them on their way (and recognize them in the future)
             */
            // PORTAL-GRAILS TODO modify this to create reasonable default users for your app
            User.withTransaction {
                def userRole
                if (userEntity.isInternalUser()) {
                    userRole = Role.findOrSaveByAuthority('ROLE_ADMIN')
                } else {
                    userRole = Role.findOrSaveByAuthority('ROLE_USER')
                }
                // PORTAL-GRAILS TODO associate this user with the client in userEntity
                user = new User([username: username, fullName: userEntity.getDisplayName(), enabled: true])
                user.save(flush: true)
                UserRole.create(user, userRole)
            }
        }

        return user
    }
}
