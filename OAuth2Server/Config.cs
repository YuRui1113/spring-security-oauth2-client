// Copyright (c) Brock Allen & Dominick Baier. All rights reserved.
// Licensed under the Apache License, Version 2.0. See LICENSE in the project root for license information.


using IdentityServer4;
using IdentityServer4.Models;
using System.Collections.Generic;

namespace IdentityServerAspNetIdentity
{
    public static class Config
    {
        static string SERVICE_NAME_BOOK = "apiBook";

        public static IEnumerable<IdentityResource> IdentityResources =>
            new List<IdentityResource>
            {
                new IdentityResources.OpenId(),
                new IdentityResources.Profile(),
            };


        public static IEnumerable<ApiScope> ApiScopes =>
            new List<ApiScope>
            {
                new ApiScope($"{SERVICE_NAME_BOOK}.read", "Book API read"),
                new ApiScope($"{SERVICE_NAME_BOOK}.write", "Book API write")
            };

        // With ApiResource you can now create two logical APIs and their correponding scopes
        public static IEnumerable<ApiResource> ApiResources =>
            new List<ApiResource>
            {
                new ApiResource
                {
                    Name = SERVICE_NAME_BOOK,
                    DisplayName = "Book API service",
                    Description = "The API for book mangement API",
                    Scopes = new List<string> { $"{SERVICE_NAME_BOOK}.read", $"{SERVICE_NAME_BOOK}.write" },
                    ApiSecrets = new List<Secret> {new Secret("secret".Sha256())}
                }
            };

        public static IEnumerable<Client> Clients =>
            new List<Client>
            {
                // machine to machine client
                new Client
                {
                    ClientId = "client",
                    ClientSecrets = { new Secret("secret".Sha256()) },

                    AllowedGrantTypes = GrantTypes.ClientCredentials,
                    // scopes that client has access to
                    AllowedScopes = { "apiBook" }
                },
                
                // interactive Angular client
                new Client
                {
                    ClientId = "angular",
                    ClientSecrets = { new Secret("secret".Sha256()) },

                    ClientUri = "http://localhost:4200", // public uri of the client
                    AllowedCorsOrigins = { "http://localhost:4200" },
                    AllowedGrantTypes = GrantTypes.Code,
                    
                    // where to redirect to after login
                    RedirectUris = { "http://localhost:4200/login-callback" },

                    // where to redirect to after logout
                    PostLogoutRedirectUris = { "http://localhost:4200/logout-callback" },

                    AllowedScopes = new List<string>
                    {
                        IdentityServerConstants.StandardScopes.OpenId,
                        IdentityServerConstants.StandardScopes.Profile,
                        $"{SERVICE_NAME_BOOK}.read"
                    },
                    // Default is true
                    RequirePkce = false,
                    AccessTokenLifetime = 60*60*2, // 2 hours
                    IdentityTokenLifetime= 60*60*2 // 2 hours
                },
                
                // interactive Spring MVC client
                new Client
                {
                    ClientId = "springMvc",
                    ClientSecrets = { new Secret("secret".Sha256()) },

                    ClientUri = "http://localhost:8081", // public uri of the client
                    AllowedCorsOrigins = { "http://localhost:8081" },
                    AllowedGrantTypes = GrantTypes.Code,
                    
                    // Specifies allowed URIs to return tokens or authorization codes to
                    RedirectUris = {  "http://localhost:8081/login/oauth2/code/springMvcClient",// For login redirect
                        "http://localhost:8081/authorize/oauth2/code/springMvcClient" }, // For authorization redirect

                    // where to redirect to after logout
                    //PostLogoutRedirectUris = { "http://localhost:8081/oauth2/logout-callback" },

                    AllowedScopes = new List<string>
                    {
                        IdentityServerConstants.StandardScopes.OpenId,
                        IdentityServerConstants.StandardScopes.Profile,
                        $"{SERVICE_NAME_BOOK}.read"
                    },
                    // Default is true
                    RequirePkce = false,
                    AccessTokenLifetime = 60*60*2, // 2 hours
                    IdentityTokenLifetime= 60*60*2 // 2 hours
                }
            };
    }
}