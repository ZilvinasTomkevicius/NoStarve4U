using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using BusinessServices;
using log4net;
using BusinessEntities;

namespace FridgeAPI.Controllers
{
    public class RecipeController : ApiController
    {
        IRecipeServices recipeServices = new RecipeServices();

        private static log4net.ILog Log { get; set; }

        ILog log = log4net.LogManager.GetLogger(typeof(RecipeController));

        /// <summary>
        /// Recipe.Add 
        /// </summary>
        /// <param name="recipe"></param>
        /// <returns></returns>
        [HttpPost]
        public HttpResponseMessage Add(RecipeEntity recipe)
        {
            try
            {
                recipeServices.Add(recipe);

                return Request.CreateResponse(HttpStatusCode.OK, recipe);
            }
            catch(Exception e)
            {
                log.Error(e);

                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        /// <summary>
        /// Recipe.Update
        /// </summary>
        /// <param name="recipe"></param>
        /// <returns></returns>
        [HttpPut]
        public HttpResponseMessage Update(RecipeEntity recipe)
        {
            try
            {
                recipeServices.Update(recipe);

                return Request.CreateResponse(HttpStatusCode.OK, recipe);
            }
            catch(Exception e)
            {
                log.Error(e);

                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        /// <summary>
        /// Recipe.Delete
        /// </summary>
        /// <param name="recipeID"></param>
        /// 
        [HttpDelete]
        public void Delete(int recipeID)
        {
            try
            {
                recipeServices.Delete(recipeID);
            }
            catch(Exception e)
            {
                log.Error(e);

                Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        /// <summary>
        /// Recipe.Get
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        public HttpResponseMessage Get(int id)
        {
            try
            {
                RecipeEntity recipe = recipeServices.Get(id);

                return Request.CreateResponse(HttpStatusCode.OK, recipe);
            }
            catch(Exception e)
            {
                log.Error(e);

                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        /// <summary>
        /// Recipe.GetList
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public HttpResponseMessage GetList()
        {
            try
            {
                var recipeList = recipeServices.GetList();

                return Request.CreateResponse(HttpStatusCode.OK, recipeList);
            }
            catch(Exception e)
            {
                log.Error(e);

                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);

            }    
        }

        /// <summary>
        /// Recipe.GetRecipeCount
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public HttpResponseMessage GetRecipeCount()
        {
            try
            {
                int count = recipeServices.GetRecipeCount();

                return Request.CreateResponse(HttpStatusCode.OK, count);
            }
            catch (Exception e)
            {
                log.Error(e);

                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }            
        }
    }
}
